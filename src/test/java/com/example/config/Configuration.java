package com.example.config;

public class Configuration {

    private static final ConfigLoader configLoader = new ConfigLoader();

    /**
     * FintanサイトのURLを取得する。
     * ローカル環境やステージング環境を対象にテストできるよう、URLを設定で変更できるようにしている。
     *
     * @return URL
     */
    public static String getFintanUrl() {
        return configLoader.get("fintan.url", "https://fintan.jp");
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
