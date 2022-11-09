package com.example;

import com.example.pages.SearchResultPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(PlaywrightExtension.class)
public class SearchResultPageTest {

    @Test
    @DisplayName("検索結果ページのタイトルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        SearchResultPage searchResultPage = new SearchResultPage(page);

        var keywords = List.of("XR", "java", "spring");
        keywords.forEach(keyword -> searchResultPage.navigate(keyword));
    }
}
