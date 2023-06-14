package com.lqs.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lqs.mall.domain.pojo.PmsBrand;

import java.util.List;

/**
* @author Admin
* @description 针对表【pms_brand(品牌表)】的数据库操作Service
* @createDate 2023-06-12 20:09:45
*/
public interface PmsBrandService extends IService<PmsBrand> {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
