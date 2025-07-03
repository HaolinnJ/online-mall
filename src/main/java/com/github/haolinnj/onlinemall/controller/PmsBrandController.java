package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.common.api.CommonPage;
import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.mbg.model.PmsBrand;
import com.github.haolinnj.onlinemall.service.IPmsBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@Tag(name = "PmsBrandController", description = "Product Brand Management")
public class PmsBrandController {
    @Autowired
    private IPmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @Operation(summary = "list all brands")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<PmsBrand>> getBrandList(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @Operation(summary = "create new brand")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
        LOGGER.info("即将插入品牌：{}", pmsBrand);
        CommonResult commonResult;

        int count = pmsBrandService.createBrand(pmsBrand);
        if(count==1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success: {}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("operation failed");
            LOGGER.debug("createBrand failed: {}", pmsBrand);
        }
        return commonResult;
    }

    @Operation(summary = "update brand by id")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult updateBrand(@PathVariable("id") Long id,
                                    @RequestBody PmsBrand pmsBrandDto,
                                    BindingResult result){
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrandDto);
        if(count==1){
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success: {}", pmsBrandDto);
        } else {
            commonResult = CommonResult.failed("operation failed");
            LOGGER.debug("updateBrand failed: {}", pmsBrandDto);
        }
        return commonResult;
    }

    @Operation(summary = "delete brand by id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public CommonResult updateBrand(@PathVariable("id") Long id){
        CommonResult commonResult;
        int count = pmsBrandService.deleteBrand(id);
        if(count==1){
            LOGGER.debug("deleteBrand success: id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed: id={}", id);
            return CommonResult.success("Operation failed");
        }
    }

    @Operation(summary = "page query")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsBrand>> listBrand(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value ="pageSize", defaultValue="3") Integer pageSize){
        List<PmsBrand> brandList = pmsBrandService.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @Operation(summary = "get detailed info of brand")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<PmsBrand> brand(@PathVariable("id") long id){
        return CommonResult.success(pmsBrandService.getBrand(id));
    }

}
