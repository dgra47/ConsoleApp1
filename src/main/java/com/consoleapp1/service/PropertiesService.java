package com.consoleapp1.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.consoleapp1.enums.PropertyEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PropertiesService {
    private final Properties properties;

    public PropertiesService() {
        this.properties = new Properties();
        try (InputStream is = PropertiesService.class.getClassLoader().getResourceAsStream("application.properties")) {
            this.properties.load(is);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public String getProperty(PropertyEnum key) {
        return this.properties.getProperty(key.getKey());
    }
}
