package com.lqs.malltiny01.service;

import com.lqs.malltiny01.mbg.model.PmsBrand;

import java.util.List;

/**
 * 2023/5/30/030  17:47
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int addBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand pmsBrandDto);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(Integer pageNum, Integer pageSize);

    PmsBrand getBrand(Long id);
}
