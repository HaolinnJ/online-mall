package com.github.haolinnj.onlinemall.common.api;

public enum ResultCode implements IErrorCode{
    SUCCESS(200,"Operation successful"),
    FAILED(500,"Operation failed"),
    VALIDATE_FAILED(404,"Parameter validation failed"),
    UNAUTHORIZED (401,"Unauthorized request"),
    FORBIDDEN(403,"Forbidden");
    private long code;
    private String message;

    private ResultCode(long code, String message){
        this.code = code;
        this.message = message;
    }

    public long getCode(){return code;}

    public String getMessage(){return message;}
}
