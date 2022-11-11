package com.example;

import com.example.pages.AuthorBlogListPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PlaywrightExtension.class)
public class AuthorBlogListPageTest {
    private static final String META_DESCRIPTION = "Fintanは、TISインテックグループが研究開発や、システム開発、新規事業開発のプロジェクトで培ったノウハウを集約したサイトです。";
    private final List<String> authorNames = List.of("XRチーム", "協業開発チーム", "新規事業開発チーム");
    @Test
    @DisplayName("著者の記事一覧ページのタイトルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        AuthorBlogListPage authorBlogListPage = new AuthorBlogListPage(page);
        authorNames.forEach(authorName -> authorBlogListPage.navigate(authorName));
    }

    @Test
    @DisplayName("metaタグのdescriptionが正しい内容であること")
    void hasCorrectMetaDescription(Page page) {
        AuthorBlogListPage authorBlogListPage = new AuthorBlogListPage(page);

        authorNames.forEach(authorName -> {
            authorBlogListPage.navigate(authorName);
            String expectMetaDescription = "著者：" + authorName + "の記事一覧ページです。" + META_DESCRIPTION;
            String actualMetaDescription = page.locator("[name=description][content]").first().getAttribute("content");
            assertEquals(expectMetaDescription, actualMetaDescription);
        });
    }
}
