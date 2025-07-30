package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.dto.PmsProductParam;
import com.github.haolinnj.onlinemall.dto.PmsProductQueryParam;
import com.github.haolinnj.onlinemall.dto.PmsProductResult;
import com.github.haolinnj.onlinemall.mbg.model.PmsProduct;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPmsProductService {
    /**
     * create product
     */
    @Transactional (isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    int create (PmsProductParam productParam);

    /**
     * get product info by id
     */
    PmsProductResult getUpdateInfo(Long id);

    /**
     * update product
     */
    @Transactional
    int update (Long id, PmsProductParam productParam);

    /**
     * query product
     */
    List<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    /**
     * update verify status
     */
    @Transactional
    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    /**
     * update publish status
     */
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * update recommend status
     */
    int updateRecommentStatus(List<Long> ids, Integer recommendStatus);

    /**
     * update new product status
     */
    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * update delete status
     */
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * query by keyword or id number
     */
    List<PmsProduct> list (String keyword);
}
