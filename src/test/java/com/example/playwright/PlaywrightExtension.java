package com.example.playwright;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * Playwright用のJUnit Extensionクラス。
 *
 * Playwright関連のインスタンスライフサイクル管理、パラメータの解決を行う。
 */
public class PlaywrightExtension implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    public static Playwright playwright;

    public static Browser browser;

    public BrowserContext browserContext;

    private final Map<Class<?>, Supplier<?>> resolver = createResolverMap();

    /**
     * {@inheritDoc}
     *
     * テスト毎に{@link BrowserContext}を用意する。
     */
    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        browserContext = browser.newContext();
    }

    /**
     * {@inheritDoc}
     *
     * {@link BrowserContext}をクローズする。
     */
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        browserContext.close();
    }

    /**
     * {@link TestExecutionListener}実装クラス。
     *
     * Playwrightリソースの生成とクローズを行う。
     * {@link java.util.ServiceLoader}の仕組みを利用しJUnitに登録を行う。
     * <pre>META-INF/services/org.junit.platform.launcher.TestExecutionListener</pre>に記載がある。
     */
    public static class PlaywrightExecutionListener implements TestExecutionListener {

        private final BrowserFactory browserFactory = new BrowserFactory();

        /**
         * {@inheritDoc}
         *
         * {@link Playwright}と{@link Browser}の生成を行う。
         */
        @Override
        public void testPlanExecutionStarted(TestPlan testPlan) {
            playwright = Playwright.create();
            browser = browserFactory.create(playwright);
        }

        /**
         * {@inheritDoc}
         *
         * {@link Playwright}と{@link Browser}のクローズを行う。
         */
        @Override
        public void testPlanExecutionFinished(TestPlan testPlan) {
            browser.close();
            playwright.close();
        }
    }

    /**
     * {@inheritDoc}
     *
     * Playwright関連の引数が指定された場合は、真を返却する。
     */
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return resolver.containsKey(parameterContext.getParameter().getType());
    }

    /**
     * {@inheritDoc}
     *
     * Playwright関連の引数が指定された場合は、対応する引数を返却する。
     */
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var supplier = resolver.get(parameterContext.getParameter().getType());
        return supplier.get();
    }

    private Map<Class<?>, Supplier<?>> createResolverMap() {
        Map<Class<?>, Supplier<?>> m = new ConcurrentHashMap<>();
        m.put(Playwright.class, () -> playwright);
        m.put(Browser.class, () -> browser);
        m.put(BrowserContext.class, () -> browserContext);
        m.put(Page.class, () -> browserContext.newPage());
        return m;
    }

}
