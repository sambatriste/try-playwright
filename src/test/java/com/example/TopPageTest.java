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
public class TopPageTest {

    @Test
    @DisplayName("トップページからカテゴリ一覧ページに遷移できること")
    void testAllCategoryList(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickCategoryLink();
    }

    @Test
    @DisplayName("トップページから記事一覧に遷移できること")
    void testAllPostsList(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.footer.clickAllPostListLink();
    }

    @Test
    @DisplayName("虫眼鏡から検索ウィンドウを開けること")
    void searchIconOpen(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.searchKeyword("java");
    }

    @Test
    @DisplayName("虫眼鏡から検索ウィンドウを閉じれること")
    void searchPanelClose(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickToCloseSearchPanel();
    }

    @Test
    @DisplayName("横幅<=500px未満でハンバーガーメニューが出る")
    void displayHamburgerMenu(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.setViewportSize(500, 1280);
        topPage.navigate();
        Locator hamburgerMenu = page.locator(".o-hamburger.js-hamburger");
        assertThat(hamburgerMenu).isVisible();
    }

    @Test
    @DisplayName("横幅>500pxでハンバーガーメニューが出ない")
    void notDisplayHamburgerMenu(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.setViewportSize(501, 1280);
        topPage.navigate();
        Locator hamburgerMenu = page.locator(".o-hamburger.js-hamburger");
        assertThat(hamburgerMenu).not().isVisible();
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
