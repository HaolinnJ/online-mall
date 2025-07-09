package com.github.haolinnj.onlinemall.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

// 存储桶访问策略的封装类 谁(Principal)能做什么(Action)，在哪(Resource)，允许还是拒绝(Effect)
@Data
@EqualsAndHashCode
@Builder
public class BucketPolicyConfigDto {

    private String Version;
    private List<Statement> Statement;

    @Data
    @EqualsAndHashCode
    @Builder
    public static class Statement{
        private String Effect;
        private String Principal;
        private String Action;
        private String Resource;
    }
}
