package com.cfz.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {

    public static <V> V copyBean(Object o, Class<V> c) {
        V res = null;
        try {
            res = c.newInstance();
            BeanUtils.copyProperties(o, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static <O,V> List<V> copyBeanList(List<O> o, Class<V> c) {
        return  o.stream()
                .map(o1 -> copyBean(o1, c))
                .collect(Collectors.toList());
    }
}
