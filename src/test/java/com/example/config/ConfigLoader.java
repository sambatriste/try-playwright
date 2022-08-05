package com.example.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

class ConfigLoader {

    private static final String ENV_LOCAL = ".env.local";

    private final Properties localProperties;

    public ConfigLoader() {
        this(loadLocalProperties());
    }

    public ConfigLoader(Properties localProperties) {
        this.localProperties = localProperties;
    }

    String get(String key) {
        return get(key, null);
    }

    String get(String key, String defaultValue) {
        String systemProp = System.getProperty(key);
        if (systemProp != null) {
            return systemProp;
        }

        String envVar = System.getenv(convertToEnvName(key));
        if (envVar != null) {
            return envVar;
        }

        String local = this.localProperties.getProperty(key);
        if (local != null) {
            return local;
        }

        return defaultValue;
    }

    private String convertToEnvName(String key) {
        return key
            .replace(".", "_")
            .replace("-", "_")
            .toUpperCase();
    }

    private static Properties loadLocalProperties() {
        var properties = new Properties();
        try {
            properties.load(Files.newBufferedReader(Paths.get(ENV_LOCAL), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            // ファイルが存在しない場合は何もしない。
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
