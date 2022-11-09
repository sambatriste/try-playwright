package com.example;

import com.example.pages.CategoryTopPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(PlaywrightExtension.class)
public class CategoryTopPageTest {

    @Test
    @DisplayName("カテゴリトップページのタイルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        CategoryTopPage categoryTopPage = new CategoryTopPage(page);

        Map<String, String> categoryPathNameMap = Map.of(
            "technical-research", "先進技術研究",
            "mobile-application", "モバイルアプリケーション開発",
            "web-application", "Webアプリケーション開発"
        );

        categoryPathNameMap.forEach((path, name) -> categoryTopPage.navigate(path, name));
    }
}
