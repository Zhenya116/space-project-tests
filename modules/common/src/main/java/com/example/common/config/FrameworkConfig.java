package com.example.common.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.DefaultValue;

@Sources({
        "system:properties",
        "classpath:${env}.properties",
        "classpath:test.properties"
})
public interface FrameworkConfig extends Config {
    @Key("api.baseUrl")
    @DefaultValue("http://localhost:8080")
    String apiBaseUrl();

    @Key("ui.baseUrl")
    @DefaultValue("https://space.example.com")
    String uiBaseUrl();
}
