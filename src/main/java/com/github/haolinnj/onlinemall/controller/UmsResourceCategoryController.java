package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.mbg.model.UmsResourceCategory;
import com.github.haolinnj.onlinemall.service.IUmsResourceCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "UmsResourceCategoryController", description = "resource category management")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Autowired
    private IUmsResourceCategoryService resourceCategoryService;

    @Operation(description = "list all resource category")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<UmsResourceCategory>> listAll(){
        List<UmsResourceCategory> resourceList = resourceCategoryService.listAll();
        return CommonResult.success(resourceList);
    }

    @Operation(description = "create resource category")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsResourceCategory umsResourceCategory){
        int count = resourceCategoryService.create(umsResourceCategory);
        if(count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(description = "update resource category")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResourceCategory umsResourceCategory){
        int count = resourceCategoryService.update(id, umsResourceCategory);
        if(count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(description = "delete resource category by id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id){
        int count = resourceCategoryService.delete(id);
        if (count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
