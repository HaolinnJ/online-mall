package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

//前台订单管理Service
public interface IOmsPortalOrderService {
    /**
     * 根据提交信息生成订单
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消单个超时订单
     */
    @Transactional
    void cancelOrder(Long orderId);
}
