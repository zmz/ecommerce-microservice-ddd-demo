package com.ecommerce.product.category;

import com.ecommerce.shared.exception.AppException;

import lombok.extern.java.Log;

import static com.ecommerce.product.ProductErrorCode.CATEGORY_NOT_FOUND;
import static com.google.common.collect.ImmutableMap.of;


@Log
public class CategoryNotFoundException extends AppException {
    public CategoryNotFoundException(String id,Throwable cause) {
        super(CATEGORY_NOT_FOUND, of("productId", id),cause);
        log.info(cause.getMessage());
    }
}
