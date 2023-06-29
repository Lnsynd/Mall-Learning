package com.lqs.mall.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by 刘千山 on 2023/6/29/029-11:14
 */
@Data
@EqualsAndHashCode
public class MinioUploadDto {

    @ApiModelProperty("文件访问的url")
    private String url;

    @ApiModelProperty("文件名称")
    private String name;

}
