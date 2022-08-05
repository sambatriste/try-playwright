package com.example.config;

public class Configuration {

    private static final ConfigLoader configLoader = new ConfigLoader();

    public static String getFintanUrl() {
        return configLoader.get("fintan.url", "https://fintan.jp");
    }

    public static String getProxyUrl() {
        return configLoader.get("https.proxy");
    }

    public static String getBrowserType() {
        return configLoader.get("playwright.browser-type", "chromium");
    }
}
