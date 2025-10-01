package utils;

import java.io.InputStream;
import java.util.Properties;

public final class Config {
    private static final Properties P = new Properties();

    static {
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) P.load(is);
        } catch (Exception ignored) {
        }
    }

    public static String baseURI() {
        String v = System.getProperty("baseURI");
        if (v != null && !v.isBlank()) return v;
        v = System.getenv("BASE_URI");
        if (v != null && !v.isBlank()) return v;
        v = P.getProperty("baseURI");
        return v != null && !v.isBlank() ? v : "http://localhost:8080";
    }

    private Config() {
    }
}

