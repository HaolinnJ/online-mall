package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

// 商品搜索管理Service
public interface IEsProductService {
    /**
     * 从数据库中导入所有商品到ElasticSearch
     */
    int importAll();

    /**
     * 根据id删除商品 (在ElasticSearch中删除）
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
