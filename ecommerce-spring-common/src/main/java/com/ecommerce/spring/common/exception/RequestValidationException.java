package com.ecommerce.spring.common.exception;

import java.util.Map;

import com.ecommerce.shared.exception.AppException;

public class RequestValidationException extends AppException {
    public RequestValidationException(Map<String, Object> data) {
        super(SpringCommonErrorCode.REQUEST_VALIDATION_FAILED, data);
    }
}
