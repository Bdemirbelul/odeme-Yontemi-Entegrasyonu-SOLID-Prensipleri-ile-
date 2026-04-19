package com.example.dynamiclog.log;

import com.example.dynamiclog.annotation.LogType;
import org.springframework.stereotype.Component;

@Component
@LogType("xml")
public class XmlLog implements Log {

    @Override
    public void write(String message) {
        System.out.println("<log>" + message + "</log>");
    }
}
