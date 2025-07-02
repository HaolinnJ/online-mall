package com.github.haolinnj.onlinemall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author HaolinnJ
 * @description MyBatis Configuration Class
 */

@Configuration
@MapperScan({"com.github.haolinnj.onlinemall.mbg.mapper", "com.github.haolinnj.onlinemall.dao"})
public class MyBatisConfig {
}

