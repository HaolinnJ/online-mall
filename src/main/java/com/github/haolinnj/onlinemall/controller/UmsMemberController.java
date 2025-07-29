package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.mbg.model.UmsMemberLevel;
import com.github.haolinnj.onlinemall.service.IUmsMemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "UmsMemberController", description = "Membership register management")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private IUmsMemberService umsMemberService;

    @Operation (summary = "get verification code")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public CommonResult getAuthCode(@RequestParam String telephone){
        return umsMemberService.generateAuthCode(telephone);
    }

    @Operation (summary = "check if verfication code is correct")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode){
        return umsMemberService.verifyAuthCode(telephone,authCode);
    }

    @Operation(summary = "query all membership level")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<UmsMemberLevel>> list (@RequestParam("defaultStatus") Integer defaultStatus){
        List<UmsMemberLevel> memberLevelList = umsMemberService.list(defaultStatus);
        return CommonResult.success(memberLevelList);
    }
}
