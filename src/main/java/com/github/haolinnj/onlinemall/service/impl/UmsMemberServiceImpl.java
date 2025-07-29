package com.github.haolinnj.onlinemall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.haolinnj.onlinemall.common.api.CommonResult;
import com.github.haolinnj.onlinemall.mbg.mapper.UmsMemberLevelMapper;
import com.github.haolinnj.onlinemall.mbg.model.UmsMemberLevel;
import com.github.haolinnj.onlinemall.mbg.model.UmsMemberLevelExample;
import com.github.haolinnj.onlinemall.service.IRedisService;
import com.github.haolinnj.onlinemall.service.IUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class UmsMemberServiceImpl implements IUmsMemberService {
    @Autowired
    private IRedisService redisService;
//    @Value("${redis.key.prefix.authCode}")
//    private String REDIS_KEY_PREFIX_AUTH_CODE;
//    @Value("${redis.key.expire.authCode}")
//    private Long AUTH_CODE_EXPIRE_SECONDS;
    @Autowired
    private UmsMemberLevelMapper memberLevelMapper;

    @Override
    public CommonResult generateAuthCode(String telephone) {
//        StringBuilder sb = new StringBuilder();
//        Random random = new Random();
//        for (int i = 0; i < 6; i++) {
//            sb.append(random.nextInt(10));
//        }
//        //验证码绑定手机号并存储到redis
//        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
//        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(null,null);
    }

    //对输入的验证码进行校验
    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
//        if (StrUtil.isEmpty(authCode)) {
//            return CommonResult.failed("Please enter verification code");
//        }
//        String realAuthCode = (String) redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
//        boolean result = authCode.equals(realAuthCode);
//        if (result) {
//            return CommonResult.success(null, "The verification code has been successfully validated.");
//        } else {
//            return CommonResult.failed("Code not correct");
//        }
        return CommonResult.success(null, null);
    }

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
