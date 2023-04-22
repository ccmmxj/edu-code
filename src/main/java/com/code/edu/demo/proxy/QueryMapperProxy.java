package com.code.edu.demo.proxy;

import com.code.edu.demo.anno.Query;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QueryMapperProxy implements InvocationHandler {

    private Map<Method, String> queryMap = new ConcurrentHashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String queryTemp = queryMap.get(method);
        if (StringUtils.isBlank(queryTemp) && method.isAnnotationPresent(Query.class)) {
            Query query = method.getAnnotation(Query.class);
            queryTemp = query.value();
            if (StringUtils.isNotBlank(queryTemp)) {
                queryMap.put(method, queryTemp);
            }
        }
        if (StringUtils.isBlank(queryTemp)) {
            return queryTemp;
        }
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (parameter.isAnnotationPresent(Param.class)) {
                Param param = parameter.getAnnotation(Param.class);
                queryTemp = queryTemp.replaceAll("\\{" + param.value() + "}", args[i].toString());
            } else {
                queryTemp = queryTemp.replaceAll("\\{" + parameter.getName() + "}", args[i].toString());
            }
        }
        return queryTemp;
    }
}
