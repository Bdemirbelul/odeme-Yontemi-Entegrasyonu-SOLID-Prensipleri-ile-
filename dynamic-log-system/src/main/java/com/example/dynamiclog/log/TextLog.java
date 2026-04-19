package com.example.dynamiclog.log;

import com.example.dynamiclog.annotation.LogType;
import org.springframework.stereotype.Component;

@Component
@LogType("text")
public class TextLog implements Log {

    @Override
    public void write(String message) {
        System.out.println("[TEXT] " + message);
    }
}
