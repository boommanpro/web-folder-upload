package com.boommanpro.webfolderupload.bindenvironment.register;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.boommanpro.webfolderupload.bindenvironment.annotation.EnableBindEnvironmentProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

/**
 * @author boommanpro
 * @date 2020/3/4 15:11
 */
@Slf4j
public class EnableBindEnvironmentPropertiesRegister implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        EnableBindEnvironmentProperties annotation = application.getMainApplicationClass().getAnnotation(EnableBindEnvironmentProperties.class);
        if (annotation == null) {
            return;
        }
        Arrays.stream(annotation.value())
                .forEach(aClass -> registerToEnvironment(propertySources, aClass));
    }

    public void registerToEnvironment(MutablePropertySources propertySources, Class<?> clazz) {
        ConfigurationProperties annotation = clazz.getAnnotation(ConfigurationProperties.class);
        if (annotation == null) {
            return;
        }
        String prefix = annotation.prefix();
        String name = String.format("%s-%s", prefix, clazz.getName());
        try {
            Properties properties = toProperties(prefix, clazz.newInstance());
            PropertySource propertySource = new PropertiesPropertySource(name, properties);
            propertySources.addLast(propertySource);
        } catch (Exception e) {
            log.error("Exception:", e);
            throw new RuntimeException();
        }

    }

    public Properties toProperties(String prefix, Object o) throws Exception {
        Properties properties = new Properties();
        Map<String, Object> map = objectToMap(o);
        map.forEach((s, o1) -> {
            properties.put(String.format("%s.%s", prefix, camelToUnderline(s)), o1);
        });

        return properties;
    }

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("-");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>(10);
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            if (value == null) {
                continue;
            }
            map.put(key, value);
        }

        return map;
    }
}
