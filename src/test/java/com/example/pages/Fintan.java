package com.example.pages;

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


    private static final String DEFAULT_URL = "https://fintan.jp";
    private static final String FINTAN_URL_KEY = "fintan.url";


    /**
     * サイトのURL。
     *
     * 明示的に指定しない場合、本番環境（{@link #DEFAULT_URL}）が使用される。
     */
    private final String url;

    /**
     * コンストラクタ。
     *
     * @param url サイトのURL
     */
    public Fintan(String url) {
        this.url = url;
    }

    /**
     * コンストラクタ。
     *
     */
    public Fintan() {
        this(resolveUrl());
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

    private static String resolveUrl() {
        return System.getProperty(FINTAN_URL_KEY, DEFAULT_URL);
    }
}
