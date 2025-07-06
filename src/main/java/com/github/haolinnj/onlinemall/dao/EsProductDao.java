package com.github.haolinnj.onlinemall.dao;

import com.github.haolinnj.onlinemall.nosql.elasticsearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//搜索系统中的商品管理自定义Dao
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}

