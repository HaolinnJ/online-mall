package com.github.haolinnj.onlinemall.dao;

import com.github.haolinnj.onlinemall.mbg.model.PmsProductVertifyRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmsProductVertifyRecordDao {
    /**
     * insert list
     */
    int insertList(@Param("list")List<PmsProductVertifyRecord> list);
}
