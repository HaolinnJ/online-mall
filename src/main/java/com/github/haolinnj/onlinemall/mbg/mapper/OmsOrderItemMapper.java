package com.github.haolinnj.onlinemall.mbg.mapper;

import com.github.haolinnj.onlinemall.mbg.model.OmsOrderItem;
import com.github.haolinnj.onlinemall.mbg.model.OmsOrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OmsOrderItemMapper {
    long countByExample(OmsOrderItemExample example);

    int deleteByExample(OmsOrderItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderItem row);

    int insertSelective(OmsOrderItem row);

    List<OmsOrderItem> selectByExample(OmsOrderItemExample example);

    OmsOrderItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") OmsOrderItem row, @Param("example") OmsOrderItemExample example);

    int updateByExample(@Param("row") OmsOrderItem row, @Param("example") OmsOrderItemExample example);

    int updateByPrimaryKeySelective(OmsOrderItem row);

    int updateByPrimaryKey(OmsOrderItem row);
}