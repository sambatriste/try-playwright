package com.example.playwright;

import com.example.config.Configuration;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BrowserFactory {

    private static Logger logger = LoggerFactory.getLogger(BrowserFactory.class);

    Browser create(Playwright playwright) {
        var browserType = chooseBrowserType(playwright);
        var launchOptions = createLaunchOptions();
        return browserType.launch(launchOptions);
    }

    private BrowserType chooseBrowserType(Playwright playwright) {
        String browserType = Configuration.getBrowserType();
        logger.info("playwright.browser-type = [" + browserType + "]");
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
        String proxyUrl = Configuration.getProxyUrl();
        logger.info("https.proxy = [" + proxyUrl + "]");
        if (proxyUrl != null) {
            launchOptions.setProxy(proxyUrl);
        }

    }

}
