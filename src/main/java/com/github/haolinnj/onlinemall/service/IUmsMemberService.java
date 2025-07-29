package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.mbg.model.UmsMemberLevel;

import java.util.List;

public interface IUmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

    /**
     * list all member level
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
