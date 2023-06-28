package com.lqs.mall.nosql.es.repository;

import com.github.pagehelper.Page;
import com.lqs.mall.nosql.es.document.EsProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by 刘千山 on 2023/6/28/028-17:30
 */

public interface EsProductRepository extends ElasticsearchRepository<EsProduct,Long> {
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
