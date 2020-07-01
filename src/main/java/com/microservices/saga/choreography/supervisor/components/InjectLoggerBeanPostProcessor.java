package com.microservices.saga.choreography.supervisor.components;

import com.microservices.saga.choreography.supervisor.Logger;
import com.microservices.saga.choreography.supervisor.annotations.InjectLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class InjectLoggerBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        process(bean);
        return bean;
    }


    private void process(Object bean) {
        var beanClass = bean.getClass();
        var fields = beanClass.getDeclaredFields();

        for (var field : fields) {
            var annotation = AnnotationUtils.getAnnotation(field, InjectLogger.class);

            // Skip irrelevant fields
            if (annotation == null) {
                continue;
            }

            // Allow access to private fields
            field.setAccessible(true);

            // Inject our logger
            ReflectionUtils.setField(field, bean, new Logger(beanClass));
        }
    }
}
