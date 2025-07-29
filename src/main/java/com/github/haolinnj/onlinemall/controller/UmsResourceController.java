package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.common.api.CommonPage;
import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.component.DynamicSecurityMetadataSource;
import com.github.haolinnj.onlinemall.mbg.model.UmsResource;
import com.github.haolinnj.onlinemall.service.IUmsResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "UmsResourceController" , description = "Resource Management")
@RequestMapping("/resource")

public class UmsResourceController {
    @Autowired
    private IUmsResourceService resourceService;
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @Operation(description = "add resource")
    @RequestMapping (value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsResource umsResource){
        int count = resourceService.create(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if(count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(description = "edit resource")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update (@PathVariable Long id,
                                @RequestBody UmsResource umsResource){
        int count = resourceService.update(id, umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if(count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(description = "get resource by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<UmsResource> getItem(@PathVariable Long id){
        UmsResource umsResource = resourceService.getItem(id);
        return CommonResult.success(umsResource);
    }

    @Operation(description = "delete resource by id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id){
        int count = resourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(description = "query resource by keyword")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<UmsResource> resourceList = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @Operation(description = "list all resource")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<UmsResource>> listAll(){
        List<UmsResource> resourceList = resourceService.listAll();
        return CommonResult.success(resourceList);
    }

}
