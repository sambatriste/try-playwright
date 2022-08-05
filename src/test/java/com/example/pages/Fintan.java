package com.example.pages;

import com.example.config.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Fintanサイトの情報を集約するクラス。
 *
 * URLを直接テストコードに埋め込むのではなく、本クラスを使用することで、
 * 本番環境、テスト環境、ローカル開発環境、いずれの環境に対してでも、
 * 同じテストコードでテストが実行できるようになる。
 *
 * システムプロパティ fintan.urlを設定しておくことで、FintanサイトのURLを変更することができる。
 *
 * （例）
 * {@code mvn -Dfintan.url=http://localhost:5000 test}
 *
 */
public class Fintan {

    private static Logger logger = LoggerFactory.getLogger(Fintan.class);

    /** サイトのURL。 */
    private final String url;

    private static final Fintan soloInstance = new Fintan();

    static Fintan getInstance() {
        return soloInstance;
    }

    /**
     * コンストラクタ。
     *
     * @param url サイトのURL
     */
    Fintan(String url) {
        this.url = url;
    }

    /**
     * コンストラクタ。
     *
     */
    Fintan() {
        this(Configuration.getFintanUrl());
        logger.info("fintan.url = [" + this.url + "]");
    }

    public String url() {
        return url;
    }

    public String pageUrl() {
        return url("/page/");
    }

    public String url(String relativePath) {
        return url + relativePath;
    }

}
