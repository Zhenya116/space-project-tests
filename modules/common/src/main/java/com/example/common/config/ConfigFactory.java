package com.example.common.config;

public class ConfigFactory {
    public static FrameworkConfig cfg() {
        return org.aeonbits.owner.ConfigFactory.create(FrameworkConfig.class, System.getProperties());
    }
}
