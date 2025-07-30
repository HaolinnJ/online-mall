package com.github.haolinnj.onlinemall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PmsProductQueryParam {
    @Schema(description = "publich status")
    private Integer publishStatus;
    @Schema(description = "verify status")
    private Integer verifyStatus;
    @Schema(description = "keyword")
    private String keyword;
    @Schema(description = "product number")
    private String productSn;
    @Schema(description = "product category id")
    private Long productCategoryId;
    @Schema(description = "product brand id")
    private Long brandId;
}
