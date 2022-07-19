package com.example.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestPlan;

import java.util.Set;

public class PlaywrightExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    public static Playwright playwright;

    public static Browser browser;

    public BrowserContext browserContext;

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        browserContext = browser.newContext();
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        browserContext.close();
    }

    private static void initializePlaywright() {
        playwright = Playwright.create();
        browser = new BrowserFactory().create(playwright);
    }

    public static void terminatePlaywright() {
        playwright.close();
    }

    public static class PlaywrightExecutionListener implements TestExecutionListener {
        @Override
        public void testPlanExecutionStarted(TestPlan testPlan) {
            initializePlaywright();
        }

        @Override
        public void testPlanExecutionFinished(TestPlan testPlan) {
            terminatePlaywright();
        }
    }


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Set<Class<?>> supported = Set.of(Playwright.class, BrowserContext.class, Browser.class, Page.class);
        return supported.contains(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> type = parameterContext.getParameter().getType();
        if (type.isAssignableFrom(Playwright.class)) {
            return playwright;
        }
        if (type.isAssignableFrom(Browser.class)) {
            return browser;
        }
        if (type.isAssignableFrom(BrowserContext.class)) {
            return browserContext;
        }
        if (type.isAssignableFrom(Page.class)) {
            return browserContext.newPage();
        }

        throw new IllegalArgumentException(type.toGenericString());

    }
}
