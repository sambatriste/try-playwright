package com.example;

import com.example.pages.CategoryTopPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@ExtendWith(PlaywrightExtension.class)
public class CategoryTopPageTest {

    private static final Map<String, String> categoryPathNameMap = Map.of(
        "technical-research", "先進技術研究",
        "mobile-application", "モバイルアプリケーション開発",
        "web-application", "Webアプリケーション開発"
    );

    @Test
    @DisplayName("カテゴリトップページのタイトルが正しい内容であること")
    void checkTitle(Page page) {
        CategoryTopPage categoryTopPage = new CategoryTopPage(page);

        categoryPathNameMap.forEach((path, name) -> categoryTopPage.navigate(path, name));
    }

    @Test
    @DisplayName("metaタグのdescriptionが正しい内容であること")
    void checkMetaDescription(Page page) {
        CategoryTopPage categoryTopPage = new CategoryTopPage(page);

        Map<String, String> categoryPathExcerptMap = Map.of(
            "technical-research", "TIS株式会社　先進技術研究チームが取り組む研究に関する活動・レポートをご紹介します。",
            "mobile-application", "TIS株式会社　テクノロジー＆イノベーション本部・モバイル開発チームがモバイル開発を実践する中で得たノウハウ・TIPS・サンプルとなる情報をご紹介します。",
            "web-application", "長年Webアプリケーション開発のアーキテクト・フルスタックエンジニアとして携わってきたTIS株式会社　テクノロジー＆イノベーション本部　エンジニアチームが、開発プロジェクトの実践を通じて培った開発ノウハウ・実践の中で活用するFW・ガイド・ツールをご紹介します。"
        );

        categoryPathExcerptMap.forEach((path, excerpt) -> categoryTopPage.checkMetaDescription(path, excerpt));
    }

    @Test
    @DisplayName("h1タグが正しい内容であること")
    void checkH1Tag(Page page) {
        CategoryTopPage categoryTopPage = new CategoryTopPage(page);

        categoryPathNameMap.forEach((path, name) -> categoryTopPage.checkH1Tag(path, name));
    }

    @Test
    @DisplayName("h2タグが正しい内容であること")
    void checkH2Tag(Page page) {
        CategoryTopPage categoryTopPage = new CategoryTopPage(page);

        categoryPathNameMap.forEach((path, name) -> categoryTopPage.checkH2Tag(path, name));
    }
}
