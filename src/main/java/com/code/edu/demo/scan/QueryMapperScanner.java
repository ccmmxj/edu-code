package com.code.edu.demo.scan;

import com.code.edu.demo.anno.QueryMapper;
import com.code.edu.demo.factory.QueryMapperFactoryBean;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.util.Arrays;
import java.util.Set;

public class QueryMapperScanner extends ClassPathBeanDefinitionScanner {

    public QueryMapperScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    /**
     * Configures parent scanner to search for the right interfaces. It can search
     * for all interfaces or just for those that extends a markerInterface or/and
     * those annotated with the annotationClass
     */
    public void registerFilters() {
        addIncludeFilter(new AnnotationTypeFilter(QueryMapper.class));
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("No MyBatis mapper was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        } else {
            processBeanDefinitions(beanDefinitions);
        }

        return beanDefinitions;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();

            if (logger.isDebugEnabled()) {
                logger.debug("Creating MapperFactoryBean with name '" + holder.getBeanName()
                        + "' and '" + definition.getBeanClassName() + "' mapperInterface");
            }
            definition.getConstructorArgumentValues().addGenericArgumentValue(definition.getBeanClassName()); // issue #59
            definition.setBeanClass(QueryMapperFactoryBean.class);
            definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        }
    }
}
