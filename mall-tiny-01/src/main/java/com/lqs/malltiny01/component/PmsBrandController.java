package com.lqs.malltiny01.component;

import com.lqs.malltiny01.common.CommonPage;
import com.lqs.malltiny01.common.CommonResult;
import com.lqs.malltiny01.mbg.model.PmsBrand;
import com.lqs.malltiny01.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2023/5/30/030  17:34
 */
@RestController
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    /**
     * @return 全部的品牌信息
     */
    @GetMapping("/list")
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }


    /**
     * @param brand 新的品牌信息
     * @return CommonResult
     */
    @PostMapping("/create")
    public CommonResult createBrand(@RequestBody PmsBrand brand) {
        CommonResult commonResult;
        int count = pmsBrandService.addBrand(brand);
        if (count == 1) {
            commonResult = CommonResult.success(brand);
            LOGGER.debug("createBrand success:{}", brand);
        } else {
            commonResult = CommonResult.failed();
            LOGGER.debug("createBrand failure:{}", brand);
        }
        return commonResult;
    }


    /**
     * 更新品牌信息
     */
    @GetMapping("/update/{id}")
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result) {
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrandDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    /**
     * 删除品牌信息
     */
    @GetMapping("/delete/{id}")
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }


    /**
     * 获取品牌分页的数据
     */
    @GetMapping("/list")
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }


    @GetMapping("/{id}")
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }


}
