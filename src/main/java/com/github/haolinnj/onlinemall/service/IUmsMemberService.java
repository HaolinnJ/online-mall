package com.github.haolinnj.onlinemall.service;

import com.github.haolinnj.onlinemall.common.api.CommonResult;

public interface IUmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);

}
