package com.github.haolinnj.onlinemall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UmsAdminParam {
    @NotEmpty
    @Schema(description = "Username", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @NotEmpty
    @Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Schema(description = "User photo")
    private String icon;
    @Email
    @Schema(description = "E-mail")
    private String email;
    @Schema(description = "Nickname")
    private String nickName;
    @Schema(description = "Note")
    private String note;
}