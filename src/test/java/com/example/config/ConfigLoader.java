package com.example.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * 設定をロードするためのクラス。
 * システムプロパティ、環境変数、.env.localファイルから設定値をロードする。
 * このファイルをバージョン管理外とすることで、公開できない情報（テスト環境のURLなど）を
 * 扱いやすくする。
 */
class ConfigLoader {

    private static final String ENV_LOCAL = ".env.local";

    /**
     * 設定値を取得する。
     * 設定が存在しない場合、デフォルト値が返却される。
     *
     * @param key キー
     * @param defaultValue デフォルト値
     * @return 値
     */
    String get(String key, String defaultValue) {
        String systemProp = System.getProperty(key);
        if (systemProp != null) {
            return systemProp;
        }

        String envVar = System.getenv(convertToEnvName(key));
        if (envVar != null) {
            return envVar;
        }

        String local = loadLocalProperties().getProperty(key);
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
        try (Reader reader = Files.newBufferedReader(Paths.get(ENV_LOCAL), StandardCharsets.UTF_8)){
            properties.load(reader);
        } catch (FileNotFoundException e) {
            // ファイルが存在しない場合は何もしない。
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return properties;
    }
}
