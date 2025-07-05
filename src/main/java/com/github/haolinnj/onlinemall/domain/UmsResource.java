package com.github.haolinnj.onlinemall.domain;

//后台资源表

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description="后台资源表")
@Builder
public class UmsResource{

    private Long id;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "资源名称")
    private String name;

    @Schema(description = "资源URL")
    private String url;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "资源分类ID")
    private Long categoryId;

}