package com.lqs.mall.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.lqs.mall.common.api.CommonResult;
import com.lqs.mall.domain.BucketPolicyConfigDto;
import com.lqs.mall.domain.MinioUploadDto;
import io.minio.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MinIO文件上传控制器
 * Created by 刘千山 on 2023/6/29/029-10:35
 */
@RestController
@RequestMapping("/minio")
@Api(tags = "MinioController", description = "MinIO对象存储管理")
public class MinioController {

    private final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);

    /**
     * MinIO的地址
     */
    @Value("${minio.endpoint}")
    private String ENDPOINT;

    /**
     * MinIO桶的名字
     */
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;

    /**
     * 访问MinIO的账号
     */
    @Value("${minio.accessKey}")
    private String ACCESS_NAME;

    /**
     * 访问MinIO的密码
     */
    @Value("${minio.secretKey}")
    private String SECRET_KEY;


    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public CommonResult<Object> upload(@RequestPart("file") MultipartFile file) {
        try {
            // 1.创建一个MinIO的Java客户端
            MinioClient client = MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(ACCESS_NAME, SECRET_KEY)
                    .build();

            // 检测桶是否存在
            boolean exists = client.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (exists) {
                LOGGER.info("存储桶已经存在!");
            } else {
                // 创建存储桶 并且设置 只读 权限
                client.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
                // 返回 权限策略 文件
                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(BUCKET_NAME);
                // 构建 存储桶 策略
                SetBucketPolicyArgs policyArgs = SetBucketPolicyArgs.builder()
                        .bucket(BUCKET_NAME)
                        .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                        .build();
                // 设置 存储策略
                client.setBucketPolicy(policyArgs);
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用PutObject上传文件到存储桶
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
            client.putObject(putObjectArgs);

            // 返回上传后文件的信息
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("上传发生错误!:{}", e.getMessage());
        }
        return CommonResult.failed();
    }

    /**
     * 创建 存储桶的 策略类
     *
     * @param bucketName 桶的名字
     * @return 返回存储桶的 策略
     */
    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject") // 设置只读权限
                .Resource("arn:aws:s3:::" + bucketName + "/*.**").build();
        return BucketPolicyConfigDto.builder()
                .Version("2012-10-17")
                .Statement(CollUtil.toList(statement))
                .build();
    }




    @ApiOperation("文件删除")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public CommonResult<Object> delete(@RequestParam("objectName") String objectName){
        try {
            // 建立Minio客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(ACCESS_NAME,SECRET_KEY).build();
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build());
            return CommonResult.success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("删除出错了！:{}",e.getMessage());
        }
        return CommonResult.failed();
    }
}
