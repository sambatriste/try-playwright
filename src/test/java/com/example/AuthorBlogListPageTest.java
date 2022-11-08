package com.example;

import com.example.pages.AuthorBlogListPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PlaywrightExtension.class)
public class AuthorBlogListPageTest {

    @Test
    @DisplayName("著者の記事一覧ページのタイルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        AuthorBlogListPage authorBlogListPage = new AuthorBlogListPage(page);

        String[] authorNames = new String[] {"XRチーム", "協業開発チーム", "新規事業開発チーム"};

        for (String authorName : authorNames) {
            authorBlogListPage.navigate(authorName);
        }
    }
}
