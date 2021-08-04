package com.accio.spring.starter.utils;

public interface CopyObjectUtil {

    /**
     * Will copy all not null properties from source to target object.
     *
     * @param source any Object
     * @param target any Object
     */
    void copyNonNullProperties(Object source, Object target);

    /**
     * Will return all property names which holds null as value.
     *
     * @param source any Object
     * @return String[]
     */
    String[] getNullPropertyNames(Object source);

}
