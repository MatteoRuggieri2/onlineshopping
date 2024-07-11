package com.mr.onlineshopping.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private T response;
    private String message;
    private int status;

    public ApiResponse() {
    }

    public ApiResponse(T response, String message, int status) {
        this.response = response;
        this.message = message;
        this.status = status;
    }

}

