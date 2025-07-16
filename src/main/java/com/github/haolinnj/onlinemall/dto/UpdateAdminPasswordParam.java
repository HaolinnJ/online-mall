package com.github.haolinnj.onlinemall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdminPasswordParam {
    @NotEmpty
    @Schema(description = "Username", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @NotEmpty
    @Schema(description = "Old Password", requiredMode = Schema.RequiredMode.REQUIRED)
    private String oldPassword;
    @NotEmpty
    @Schema(description = "New Password", requiredMode = Schema.RequiredMode.REQUIRED)
    private String newPassword;
}
