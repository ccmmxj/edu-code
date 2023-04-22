package com.code.edu.demo.factory;

import com.code.edu.demo.proxy.QueryMapperProxy;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class QueryMapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    private static QueryMapperProxy proxy = new QueryMapperProxy();

    public QueryMapperFactoryBean(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface}, proxy);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
