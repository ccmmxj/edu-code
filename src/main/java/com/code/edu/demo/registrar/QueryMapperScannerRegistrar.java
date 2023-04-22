package com.code.edu.demo.registrar;

import com.code.edu.demo.anno.QueryMapperScan;
import com.code.edu.demo.scan.QueryMapperScanner;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

public class QueryMapperScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        QueryMapperScanner scanner = new QueryMapperScanner(registry);

        if (this.resourceLoader != null) {
            scanner.setResourceLoader(this.resourceLoader);
        }

        scanner.registerFilters();
        AnnotationAttributes annoAttrs = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(QueryMapperScan.class.getName()));
        String[] pkgs = annoAttrs.getStringArray("value");
        scanner.doScan(pkgs);
    }
}
