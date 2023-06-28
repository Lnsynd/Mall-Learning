package com.lqs.mall.service;

import com.lqs.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * Created by 刘千山 on 2023/6/28/028-15:17
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
