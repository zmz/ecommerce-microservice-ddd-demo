package com.ecommerce.shared.utils;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

//Http request command of list as root element should be wrapped in this class in order to be validated by JSR-303
public class ListCommand<T> extends ArrayList<T> {
    @Valid
    public List<T> getList() {
        return this;
    }
}
