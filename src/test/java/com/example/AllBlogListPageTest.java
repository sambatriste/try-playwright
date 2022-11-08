package com.example;

import com.example.pages.AllBlogListPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PlaywrightExtension.class)
public class AllBlogListPageTest {

    @Test
    @DisplayName("記事の総一覧リストページのタイルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        AllBlogListPage allBlogListPage = new AllBlogListPage(page);
        allBlogListPage.navigate();
    }
}
