package com.github.haolinnj.onlinemall.service.impl;

import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.component.CancelOrderSender;
import com.github.haolinnj.onlinemall.dto.OrderParam;
import com.github.haolinnj.onlinemall.service.IOmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmsPortalOrderServiceImpl implements IOmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam){
        //此处还要补充一系列订单操作
        LOGGER.info("process generateOrder");
        sendDelayMessageCancelOrder(11L);
        return CommonResult.success(null,"Ordered successful");
    }

    @Override
    public void cancelOrder(Long orderId){
        //此处还要补充一系列取消订单操作
        LOGGER.info("process cancelOrder orderId:{}", orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        //获取订单超时时间，假设为60分钟(测试用的30秒)
        long delayTimes = 30 * 1000;
        //发送延迟消息
        cancelOrderSender.sendMessage(orderId, delayTimes);
    }

}
