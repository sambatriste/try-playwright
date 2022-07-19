package com.example;

import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


@ExtendWith(PlaywrightExtension.class)
public class MyTest {

    @Test
    @DisplayName("トップページから記事一覧に遷移できること")
    void testAllPostsList(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.clickAllPostListLink();
    }


    @Test
    @DisplayName("記事の検索ができること")
    void searchButton(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.searchKeyword("java");
    }
}
