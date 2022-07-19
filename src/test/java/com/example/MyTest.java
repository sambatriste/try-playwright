package com.example;

import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

public class MyTest {

    @RegisterExtension
    static PlaywrightExtension ex = new PlaywrightExtension();



    @Test
    @DisplayName("トップページから記事一覧に遷移できること")
    void testAllPostsList(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.clickAllPostListLink();
    }


    @Test
    void searchButton(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.searchKeyword("java");

    }
}
