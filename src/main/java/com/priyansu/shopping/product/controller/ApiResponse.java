package com.priyansu.shopping.product.controller;

import lombok.Getter;

@Getter
public class ApiResponse {
    private final int status;
    private final String message;
    private final Object data;

    public ApiResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
