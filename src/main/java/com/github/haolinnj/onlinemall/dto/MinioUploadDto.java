package com.github.haolinnj.onlinemall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MinioUploadDto {
    @Schema(description = "url address")
    private String url; // 上传到minio之后的url地址
    @Schema(description = "document name")
    private String name; // 上传之后的文件名
}
