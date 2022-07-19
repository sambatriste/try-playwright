package com.example;

public class Fintan {

    private final String url;

    public Fintan(String url) {
        this.url = url;
    }

    public Fintan() {
        this(System.getProperty("fintan.url", "https://fintan.jp"));
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
