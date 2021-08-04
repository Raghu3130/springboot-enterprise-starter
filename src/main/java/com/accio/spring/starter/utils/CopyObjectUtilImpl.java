package com.accio.spring.starter.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CopyObjectUtilImpl implements CopyObjectUtil {

    /**
     * Will copy all not null properties from source to target object.
     *
     * @param source The source object to copy properties from
     * @param target The target object to copy properties in
     */
    @Override
    public void copyNonNullProperties(
            final Object source,
            final Object target
    ) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    /**
     * Will return all property names which holds null as value.
     *
     * @param source instance of any class to filter the property name of null
     *               values
     * @return String[]
     */
    @Override
    public String[] getNullPropertyNames(final Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

}
