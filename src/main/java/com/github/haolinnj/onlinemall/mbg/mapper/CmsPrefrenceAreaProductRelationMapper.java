package com.github.haolinnj.onlinemall.mbg.mapper;

import com.github.haolinnj.onlinemall.mbg.model.CmsPrefrenceAreaProductRelation;
import com.github.haolinnj.onlinemall.mbg.model.CmsPrefrenceAreaProductRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsPrefrenceAreaProductRelationMapper {
    long countByExample(CmsPrefrenceAreaProductRelationExample example);

    int deleteByExample(CmsPrefrenceAreaProductRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CmsPrefrenceAreaProductRelation row);

    int insertSelective(CmsPrefrenceAreaProductRelation row);

    List<CmsPrefrenceAreaProductRelation> selectByExample(CmsPrefrenceAreaProductRelationExample example);

    CmsPrefrenceAreaProductRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") CmsPrefrenceAreaProductRelation row, @Param("example") CmsPrefrenceAreaProductRelationExample example);

    int updateByExample(@Param("row") CmsPrefrenceAreaProductRelation row, @Param("example") CmsPrefrenceAreaProductRelationExample example);

    int updateByPrimaryKeySelective(CmsPrefrenceAreaProductRelation row);

    int updateByPrimaryKey(CmsPrefrenceAreaProductRelation row);
}