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
 * 設定値 fintan.urlを設定しておくことで、FintanサイトのURLを変更することができる。
 *
 * （例）
 * {@code mvn -Dfintan.url=http://localhost:5000 test}
 *
 * @see Configuration#getFintanUrl()
 */
public class Fintan {

    private static Logger logger = LoggerFactory.getLogger(Fintan.class);

    /**
     * サイトのURL。
     */
    private final String url;

    private static final Fintan soloInstance = new Fintan();

    public static Fintan getInstance() {
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
     */
    Fintan() {
        this(Configuration.getFintanUrl());
        logger.info("fintan.url = [" + this.url + "]");
    }

    /**
     * FintanサイトのURLを取得する。
     * テスト対象とする環境に応じたURLが返却される。
     *
     * @return FintanサイトのURL
     */
    public String url() {
        return url;
    }

    /**
     * 記事のURLを取得する。
     *
     * @return 個別記事のURL
     */
    public String pageUrl() {
        return url("/page/");
    }

    /**
     * 指定したIDに対応する記事のURLを取得する。
     *
     * @param pageId ID
     * @return URL
     */
    public String pageUrl(String pageId) {
        return pageUrl() + pageId + "/";
    }

    /**
     * 相対パスを付与したURLを取得する。
     *
     * @param relativePath 相対パス
     * @return 相対パスを付与したURL
     */
    public String url(String relativePath) {
        return url + relativePath;
    }

}
