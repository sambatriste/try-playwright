package com.example;

import com.example.pages.TopPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


@ExtendWith(PlaywrightExtension.class)
public class MyTest {

    @Test
    @DisplayName("トップページから記事一覧に遷移できること")
    void testAllPostsList(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.footer.clickAllPostListLink();
    }

    @Test
    @DisplayName("記事の検索ができること")
    void searchButton(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.searchKeyword("java");
    }

    @Test
    @DisplayName("最新記事の「もっと見る」をクリックすると記事が追加で表示されること")
    void readMore(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();

        Locator cards = topPage.getLatestArticleCards();
        assertThat(cards).hasCount(8);

        topPage.clickLatestArticleReadMore();
        assertThat(cards).hasCount(16);

    }
}
