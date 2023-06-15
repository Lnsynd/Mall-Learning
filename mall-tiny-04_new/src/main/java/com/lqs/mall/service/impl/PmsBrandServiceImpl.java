package com.lqs.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.mall.domain.pojo.PmsBrand;
import com.lqs.mall.mapper.PmsBrandMapper;
import com.lqs.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Admin
* @description 针对表【pms_brand(品牌表)】的数据库操作Service实现
* @createDate 2023-06-12 20:09:45
*/
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand>
    implements PmsBrandService{
    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        LambdaQueryWrapper<PmsBrand> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        return brandMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insert(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand brand) {
        brand.setId(id);
        return brandMapper.updateById(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteById(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        Page<PmsBrand> page = new Page<>(pageNum,pageSize);
        page(page);
        return page.getRecords();
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectById(id);
    }
}




