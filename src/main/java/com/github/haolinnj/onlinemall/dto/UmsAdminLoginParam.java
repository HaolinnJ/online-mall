package com.github.haolinnj.onlinemall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class UmsAdminLoginParam {
    @NotEmpty
    @Schema(description = "Username", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @NotEmpty
    @Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}

