package com.example.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

class BrowserFactory {

    Browser create(Playwright playwright) {
        var browserType = chooseBrowserType(playwright);
        var launchOptions = createLaunchOptions();
        return browserType.launch(launchOptions);
    }

    private BrowserType chooseBrowserType(Playwright playwright) {
        String browserType = System.getProperty("playwright.browser-type", "chromium");
        return switch (browserType) {
            case "chromium" -> playwright.chromium();
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> throw new IllegalArgumentException(browserType);
        };
    }

    private BrowserType.LaunchOptions createLaunchOptions() {
        BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                //.setHeadless(false)
                .setSlowMo(0);// ms
        registerProxy(launchOptions);
        return launchOptions;
    }

    private void registerProxy(BrowserType.LaunchOptions launchOptions) {
        String proxyUrl = System.getenv("HTTPS_PROXY");
        if (proxyUrl != null) {
            launchOptions.setProxy(proxyUrl);
        }

    }

}
