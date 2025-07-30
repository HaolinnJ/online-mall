package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.dto.PmsProductParam;
import com.github.haolinnj.onlinemall.service.IPmsProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "PmsProductController", description = "product management")
@RequestMapping("/product")
public class PmsProductController {
    @Autowired
    IPmsProductService pmsProductService;

    @Operation(description = "create product")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create (@RequestBody PmsProductParam productParam){
        int count = pmsProductService.create(productParam);
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }


}
