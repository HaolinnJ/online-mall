package com.github.haolinnj.onlinemall.controller;

import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.nosql.mongodb.document.MemberReadHistory;
import com.github.haolinnj.onlinemall.service.IMemberReadHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "MemberReadHistoryController", description = "Read History Management")
@RequestMapping("/member/readHistory")
public class MemberReadHistoryController {
    @Autowired
    private IMemberReadHistoryService memberReadHistoryService;

    @Operation(summary = "create read history")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create (@RequestBody MemberReadHistory memberReadHistory){
        int count = memberReadHistoryService.create(memberReadHistory);
        if(count>0){
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "delete read history")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids")List<String> ids){
        int count = memberReadHistoryService.delete(ids);
        if(count>0){
            return CommonResult.success(count);
        } else{
            return CommonResult.failed();
        }
    }

    @Operation(summary = "list read history by member ID")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<MemberReadHistory>> list(Long memberId){
        List<MemberReadHistory> memberReadHistoryList = memberReadHistoryService.list(memberId);
        return CommonResult.success(memberReadHistoryList);
    }
}
