package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.common.api.CommonPage;
import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.mbg.model.UmsRole;
import com.github.haolinnj.onlinemall.service.IUmsRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "UmsRoleController", description = "User role management")
@RequestMapping("/role")

public class UmsRoleController {
    @Autowired
    private IUmsRoleService roleService;

    @Operation(description = "add role")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsRole role){
        int count = roleService.create(role);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(description = "update role")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsRole role){
        int count = roleService.update(id, role);
        if(count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(description = "delete role")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids")List<Long> ids){
        int count = roleService.delete(ids);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(description = "list all roles")
    @RequestMapping(value = "/listAll", method = RequestMethod.POST)
    public CommonResult<List<UmsRole>> listAll(){
        List<UmsRole> roleList = roleService.list();
        return CommonResult.success(roleList);
    }

    @Operation(description = "get role list by keyword")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<UmsRole> roleList = roleService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @Operation(description = "update role status")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    public CommonResult updateStatus (@PathVariable Long id, @RequestParam(value = "status") Integer status){
        UmsRole umsRole = new UmsRole();
        umsRole.setStatus(status);
        int count = roleService.update(id, umsRole);
        if(count > 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}
