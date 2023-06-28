package com.lqs.mall.dao;

import com.lqs.mall.nosql.es.document.EsProduct;

import java.util.List;

/**
 * Created by 刘千山 on 2023/6/28/028-17:39
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(Object o);
}
