package com.github.haolinnj.onlinemall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

public class PmsProductResult extends PmsProductParam{
    @Getter
    @Setter
    @Schema(description = "parent id of product")
    private Long cateParentId;
}
