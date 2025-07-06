package com.github.haolinnj.onlinemall.nosql.elasticsearch.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode
@Document(indexName = "pms")
@Setting(shards = 1,replicas = 0)
//搜索商品的信息
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private long id;
    @Field(type = FieldType.Keyword) //这些字段会被存储为关键字类型，只会精确匹配不会被分词
    private String productSn; //产品序列号 关键字类型
    private Long brandId; //非关键字类型
    @Field(type = FieldType.Keyword)
    private String brandName; //品牌名称 关键字类型
    private Long productCategoryId; //非关键字类型
    @Field(type = FieldType.Keyword)
    private String productCategoryName; //产品分类名称 关键字类型
    private String pic;
    //这些字段会被存储为文本类型，会被分词，适用于全文搜索
    //ik_max_word是一个流行的中文分词器
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    // 每个EsProductAttributeValue都将作为一个独立可查询的嵌套文档存储在Elasticsearch中
    @Field(type =FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;
}
