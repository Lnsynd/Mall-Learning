package com.lqs.malltiny01.service.impl;

import com.github.pagehelper.PageHelper;
import com.lqs.malltiny01.mbg.mapper.PmsBrandMapper;
import com.lqs.malltiny01.mbg.model.PmsBrand;
import com.lqs.malltiny01.mbg.model.PmsBrandExample;
import com.lqs.malltiny01.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2023/5/30/030  17:47
 */
@Service
public class PmsBrandServiceImpl implements PmsBrandService {

    @Autowired
    PmsBrandMapper mapper;
    @Override
    public List<PmsBrand> listAllBrand() {
        return mapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int addBrand(PmsBrand brand) {
        return mapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return mapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return mapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
