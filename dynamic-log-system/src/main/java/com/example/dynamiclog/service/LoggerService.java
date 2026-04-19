package com.example.dynamiclog.service;

import com.example.dynamiclog.annotation.LogType;
import com.example.dynamiclog.log.Log;
import jakarta.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;

/**
 * Classpath taraması + reflection ile {@link LogType} işaretli sınıfları bulur;
 * Spring context üzerinden doğru {@link Log} bean'ini seçer.
 */
@Service
public class LoggerService {

    private static final String LOG_PACKAGE = "com.example.dynamiclog.log";

    private final ApplicationContext applicationContext;
    private final Map<String, Log> loggersByType = new HashMap<>();

    public LoggerService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void registerLogImplementations() {
        ClassPathScanningCandidateComponentProvider scanner =
                new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(LogType.class));

        for (BeanDefinition candidate : scanner.findCandidateComponents(LOG_PACKAGE)) {
            String className = candidate.getBeanClassName();
            try {
                Class<?> clazz = Class.forName(className);
                if (!Log.class.isAssignableFrom(clazz)) {
                    continue;
                }
                LogType annotation = clazz.getAnnotation(LogType.class);
                if (annotation == null) {
                    continue;
                }
                @SuppressWarnings("unchecked")
                Class<? extends Log> logClass = (Class<? extends Log>) clazz;
                Log logBean = applicationContext.getBean(logClass);
                loggersByType.put(annotation.value().toLowerCase(), logBean);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Sınıf yüklenemedi: " + className, e);
            }
        }
    }

    public void log(String type, String message) {
        Log impl = loggersByType.get(type.toLowerCase());
        if (impl == null) {
            throw new IllegalArgumentException(
                    "Bilinmeyen log tipi: " + type + ". Mevcut tipler: " + getRegisteredTypes());
        }
        impl.write(message);
    }

    public Set<String> getRegisteredTypes() {
        return loggersByType.keySet().stream().sorted().collect(Collectors.toUnmodifiableSet());
    }

    public Map<String, Log> getLoggersByType() {
        return Collections.unmodifiableMap(loggersByType);
    }
}
