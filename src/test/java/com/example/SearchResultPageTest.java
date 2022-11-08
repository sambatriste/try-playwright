package com.example;

import com.example.pages.SearchResultPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PlaywrightExtension.class)
public class SearchResultPageTest {

    @Test
    @DisplayName("検索結果ページのタイルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        SearchResultPage searchResultPage = new SearchResultPage(page);

        String[] keywords = new String[] {"XR", "java", "spring"};

        for (String keyword: keywords) {
            searchResultPage.navigate(keyword);
        }
    }
}
