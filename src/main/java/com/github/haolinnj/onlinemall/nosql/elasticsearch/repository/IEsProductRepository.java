package com.github.haolinnj.onlinemall.nosql.elasticsearch.repository;

import com.github.haolinnj.onlinemall.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

//商品ES操作类
//EsProduct: 第一个参数 EsProduct 指定了这个 Repository 将操作的数据类型。这意味着 IEsProductRepository 会处理 EsProduct 类的对象，并将它们映射到 Elasticsearch 中的文档
//Long: 第二个参数 Long 指定了 EsProduct 类中作为文档 ID 的字段类型。回顾之前的 EsProduct 类，它的 @Id 字段是 Long 类型的 id。
public interface IEsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
    /**
     * 搜索查询
     * @param name 商品名称
     * @param subTitle 商品标题
     * @param keywords 商品关键字
     * @param page 分页信息
     * @return
     */
    //Spring Data 会根据您定义的方法名称，自动解析并构建相应的 Elasticsearch 查询
    //findBy：这是约定好的前缀，表示这是一个查询操作。
    //
    //NameOrSubTitleOrKeywords：这是关键部分。它告诉 Spring Data Elasticsearch：
    //
    //在 name 字段中搜索 name 参数的值。
    //
    //或者 (Or) 在 subTitle 字段中搜索 subTitle 参数的值。
    //
    //或者 (Or) 在 keywords 字段中搜索 keywords 参数的值。
    //
    //这意味着只要商品的名称、副标题或关键字中包含您传入的任何一个参数，它就会被搜索出来。
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name,
                                                   String subTitle,
                                                   String keywords,
                                                   Pageable page);
}
