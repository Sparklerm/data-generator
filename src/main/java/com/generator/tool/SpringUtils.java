package com.generator.tool;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Sparkler
 * @createDate 2022/11/13
 */

@Component
public class SpringUtils implements ApplicationContextAware {
    @Getter
    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> clazz) {
        return SpringUtils.applicationContext.getBean(clazz);
    }

    public static Object getBean(String name) {
        return SpringUtils.applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return SpringUtils.applicationContext.getEnvironment().getProperty(key);
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        if (SpringUtils.applicationContext == null) {
            SpringUtils.applicationContext = applicationContext;
        }
    }

}
