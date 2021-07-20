package com.accio.spring.starter.utils;

public interface CopyObjectUtil {
    void copyNonNullProperties(Object source, Object target);
    String[] getNullPropertyNames(Object source);
}
