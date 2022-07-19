package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class PlaywrightSupport {

    protected static final String TOP_PAGE = "https://fintan.jp";

    protected String path(String pathFromRoot) {
        return TOP_PAGE + pathFromRoot;
    }

    private static final BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
            .setHeadless(true)
            .setSlowMo(0); // ms

    // Shared between all tests in this class.
    static Playwright playwright;

    static Browser browser;

    BrowserContext browserContext;



    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(launchOptions);
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        browserContext = browser.newContext();
    }

    @AfterEach
    void closeContext() {
        browserContext.close();
    }

    Page newPage() {
        return browserContext.newPage();
    }
}
