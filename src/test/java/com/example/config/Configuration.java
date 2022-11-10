package com.example.config;

public class Configuration {
    public static String PRODUCTION_URL = "https://fintan.jp";
    private static final ConfigLoader configLoader = new ConfigLoader();

    /**
     * FintanサイトのURLを取得する。
     * ローカル環境やステージング環境を対象にテストできるよう、URLを設定で変更できるようにしている。
     *
     * @return URL
     */
    public static String getFintanUrl() {
        return configLoader.get("fintan.url", PRODUCTION_URL);
    }

    /**
     * テスト対象が本番環境であるか判定する。
     * {@link #getFintanUrl()}で取得したURLが本番環境のURL({@link #PRODUCTION_URL})と一致した場合、
     * 本番環境を対象としたテストとみなす。
     *
     * @return テスト対象が本番環境の場合、真
     */
    public static boolean isProductionEnvironment() {
        return getFintanUrl().equals(PRODUCTION_URL);
    }

    /**
     * プロキシURLを取得する。
     * @return プロキシURL（設定されていない場合、null）
     */
    public static String getProxyUrl() {
        return configLoader.get("https.proxy", null);
    }

    /**
     * ブラウザ種別(chromium, firefox, webkit)を取得する。
     * {@link com.microsoft.playwright.BrowserType}を決めるために使用される。
     *
     * @return ブラウザ種別（設定されていない場合、chromium）
     */
    public static String getBrowserType() {
        return configLoader.get("playwright.browser-type", "chromium");
    }
}
