package com.github.haolinnj.onlinemall.service.impl;

import com.github.haolinnj.onlinemall.dao.PmsProductVertifyRecordDao;
import com.github.haolinnj.onlinemall.dto.PmsProductParam;
import com.github.haolinnj.onlinemall.dto.PmsProductQueryParam;
import com.github.haolinnj.onlinemall.dto.PmsProductResult;
import com.github.haolinnj.onlinemall.mbg.mapper.PmsProductMapper;
import com.github.haolinnj.onlinemall.mbg.model.PmsProduct;
import com.github.haolinnj.onlinemall.mbg.model.PmsProductExample;
import com.github.haolinnj.onlinemall.mbg.model.PmsProductVertifyRecord;
import com.github.haolinnj.onlinemall.service.IPmsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PmsProductServiceImpl implements IPmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductVertifyRecordDao productVertifyRecordDao;

    @Override
    public int create(PmsProductParam productParam) {
        int count;
        // create product
        PmsProduct product = productParam;
        product.setId(null);
        productMapper.insertSelective(product);
        count = 1;
        return count;
    }

    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return null;
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        return 0;
    }

    @Override
    public List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        return List.of();
    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        PmsProduct product = new PmsProduct();
        product.setVerifyStatus(verifyStatus);
        PmsProductExample example = new PmsProductExample();
        example.createCriteria().andIdIn(ids);
        List<PmsProductVertifyRecord> list = new ArrayList<>();
        int count = productMapper.updateByExampleSelective(product,example);
        // add record
        for (Long id : ids){
            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            list.add(record);
        }
        productVertifyRecordDao.insertList(list);
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        return 0;
    }

    @Override
    public int updateRecommentStatus(List<Long> ids, Integer recommendStatus) {
        return 0;
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        return 0;
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        return 0;
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        return List.of();
    }
}
