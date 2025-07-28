package com.github.haolinnj.onlinemall.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.haolinnj.onlinemall.common.api.CommonPage;
import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.dto.UmsMenuNode;
import com.github.haolinnj.onlinemall.mbg.model.UmsMenu;
import com.github.haolinnj.onlinemall.service.IUmsMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Tag(name = "UmsMenuController", description = "Menu Management")
@RequestMapping("/menu")
public class UmsMenuController {
    @Autowired
    private IUmsMenuService menuService;
    @Autowired
    private ObjectMapper configuredObjectMapper;

    @Operation(summary = "add menu")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsMenu umsMenu){
        System.out.println("DEBUG: Injected ObjectMapper instance hash: " + configuredObjectMapper.hashCode());
        System.out.println("DEBUG: Injected ObjectMapper instance type: " + configuredObjectMapper.getClass().getName());
        System.out.println("DEBUG: UmsMenu class loaded from: " + umsMenu.getClass().getProtectionDomain().getCodeSource().getLocation());
        System.out.println("FINAL CHECK: @RequestBody UmsMenu object: " + umsMenu);
        System.out.println("FINAL CHECK: @RequestBody UmsMenu parentId: " + (umsMenu != null ? umsMenu.getParentId() : "umsMenu is null"));
        System.out.println("FINAL CHECK: @RequestBody UmsMenu title: " + (umsMenu != null ? umsMenu.getTitle() : "umsMenu is null"));
        int count = menuService.create(umsMenu);
        if(count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "update menu")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsMenu umsMenu){
        int count = menuService.update(id, umsMenu);
        if(count > 0){
            return CommonResult.success(count);
        } else{
            return CommonResult.failed();
        }
    }

    @Operation(summary = "get menu by id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<UmsMenu> getItem (@PathVariable Long id){
        UmsMenu umsMenu = menuService.getItem(id);
        return CommonResult.success(umsMenu);
    }

    @Operation(summary = "delete menu by id")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public  CommonResult delete(@PathVariable Long id){
        int count = menuService.delete(id);
        if(count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "query menu list")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam (value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam (value = "pageNum", defaultValue = "1") Integer pageNum){
        List<UmsMenu> menuList = menuService.list(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(menuList));
    }

    @Operation(summary = "return all menu list in a tree list")
    @RequestMapping(value = "/treelist", method = RequestMethod.GET)
    public CommonResult<List<UmsMenuNode>> treeList(){
        List<UmsMenuNode> list = menuService.treelist();
        return CommonResult.success(list);
    }

    @Operation(summary = "update menu status")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    public  CommonResult updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden){
        int count = menuService.updateHidden(id, hidden);
        if(count > 0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}
