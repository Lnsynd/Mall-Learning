package com.lqs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lqs.entity.PmsBrand;
import com.lqs.mapper.PmsBrandMapper;
import com.lqs.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author 刘千山
 * @since 2023-05-31
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {
    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        return brandMapper.selectList(queryWrapper);
    }

    @Override
    public int createBrand(PmsBrand brand) {
        return brandMapper.insert(brand);
    }

    @Override
    public int updateBrand(PmsBrand brand) {
        return brandMapper.updateById(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteById(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        Page<PmsBrand> page = new Page<PmsBrand>(pageNum,pageSize);
        page(page);
        return page.getRecords();
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectById(id);
    }
}
