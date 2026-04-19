package com.example.dynamiclog.controller;

import com.example.dynamiclog.service.LoggerService;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogDemoController {

    private final LoggerService loggerService;

    public LogDemoController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    /**
     * Örnek: GET /api/log?type=db&message=Merhaba
     */
    @GetMapping("/log")
    public ResponseEntity<Map<String, String>> log(
            @RequestParam String type, @RequestParam(defaultValue = "test mesajı") String message) {
        loggerService.log(type, message);
        return ResponseEntity.ok(
                Map.of("ok", "true", "type", type, "message", message));
    }

    @GetMapping("/log-types")
    public Map<String, Object> listTypes() {
        return Map.of("registeredTypes", loggerService.getRegisteredTypes());
    }
}
