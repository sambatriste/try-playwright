package com.example;

import com.example.pages.SearchResultPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PlaywrightExtension.class)
public class SearchResultPageTest {
    private static final String META_DESCRIPTION = "Fintanは、TISインテックグループが研究開発や、システム開発、新規事業開発のプロジェクトで培ったノウハウを集約したサイトです。研究成果や、PJ推進のプラクティス、要件定義/設計/プログラミング/テストといった作業のプラクティス、成果物のテンプレート/サンプル、各種開発ツールを提供します。Fintanは、どなたでも無償でご利用いただけます。";

    private final List<String> keywords = List.of("XR", "java", "spring");
    @Test
    @DisplayName("検索結果ページのタイトルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        SearchResultPage searchResultPage = new SearchResultPage(page);
        keywords.forEach(keyword -> searchResultPage.navigate(keyword));
    }

    @Test
    @DisplayName("検索結果ページ、metaタグのdescriptionが正しい内容であること")
    void hasCorrectMetaDescription(Page page) {
        SearchResultPage searchResultPage = new SearchResultPage(page);
        keywords.forEach(keyword -> {
            searchResultPage.navigate(keyword);
            String actualMetaDescription = page.locator("[name=description][content]").first().getAttribute("content");
            assertEquals(META_DESCRIPTION, actualMetaDescription);
        });
    }
}
