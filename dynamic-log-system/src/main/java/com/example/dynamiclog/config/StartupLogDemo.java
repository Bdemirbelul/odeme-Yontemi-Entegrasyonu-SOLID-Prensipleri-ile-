package com.example.dynamiclog.config;

import com.example.dynamiclog.service.LoggerService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * İsteğe bağlı: application.properties içinde {@code app.log-demo=true} ile
 * uygulama açılışında konsolda örnek log yazar (ödevde Controller yeterliyse kapatabilirsiniz).
 */
@Component
@ConditionalOnProperty(name = "app.log-demo", havingValue = "true")
public class StartupLogDemo implements ApplicationRunner {

    private final LoggerService loggerService;

    public StartupLogDemo(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @Override
    public void run(ApplicationArguments args) {
        loggerService.log("db", "Başlangıç testi (db)");
        loggerService.log("xml", "Başlangıç testi (xml)");
        loggerService.log("text", "Başlangıç testi (text)");
    }
}
