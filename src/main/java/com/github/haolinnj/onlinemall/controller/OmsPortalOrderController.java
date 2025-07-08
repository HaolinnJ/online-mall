package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.dto.OrderParam;
import com.github.haolinnj.onlinemall.service.IOmsPortalOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag (name = "OmsPortalOrderController", description = "Order Management")
@RequestMapping("/order")
public class OmsPortalOrderController {
    @Autowired
    private IOmsPortalOrderService portalOrderService;

    @Operation(summary = "generate order based on cart")
    @RequestMapping(value = "/generateOrder", method = RequestMethod.POST)
    public Object generateOrder(@RequestBody OrderParam orderParam){
        return portalOrderService.generateOrder(orderParam);
    }
}
