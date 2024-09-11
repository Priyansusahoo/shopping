package com.priyansu.shopping.category.exception;

import java.lang.RuntimeException;

public class CategoryServiceException extends RuntimeException{
    public CategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
