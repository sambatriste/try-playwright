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
    void clickCategoryLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickCategoryLink();
    }

    @Test
    @DisplayName("トップページから記事一覧に遷移できること")
    void clickAllPostListLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.footer.clickAllPostListLink();
    }

    @Test
    @DisplayName("虫眼鏡から検索ウィンドウを開けること")
    void searchPanelOpen(Page page) {
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

    @Test
    @DisplayName("最新記事の「短く表示する」をクリックすると記事が追加で表示されること")
    void displayBlogsShortly(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.clickDisplayLatestArticlesShortly();
    }

    @Test
    @DisplayName("最新記事リストの記事にメインカテゴリ、タイトル、抜粋内容、タグ、著者アイコン、著者名、日付、いいね数が表示されていること")
    void blogDetailsArelVisible(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        // メインカテゴリ
        assertThat(page.locator("li.o-card > .o-card__content > a >.o-card__head > .o-card__category").first()).isVisible();
        // タイトル
        assertThat(page.locator("li.o-card > .o-card__content > a >.o-card__title").first()).isVisible();
        // 抜粋内容
        assertThat(page.locator("li.o-card > .o-card__content > a >.o-card__text").first()).isVisible();
        // タグ
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__tag").first()).isVisible();
        // 著者アイコン
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__user > .image").first()).isVisible();
        // 著者名
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__user > .info > h5").first()).isVisible();
        // 日付
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__user > .info > .bottom > time").first()).isVisible();
        // いいね数
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__user > .info > .bottom > .like").first()).isVisible();
    }

    @Test
    @DisplayName("リンク押下で該当記事に遷移できる")
    void clickBlogLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        Locator blogLink = page.locator("li.o-card > .o-card__content > a").first();
        String destinationUrl = blogLink.getAttribute("href");
        blogLink.click();
        assertThat(page).hasURL(destinationUrl);
    }

    @Test
    @DisplayName("カテゴリリンクをホバーしてカテゴリ一覧が表示されること")
    void hoverCategoryLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.hoverCategoryLink();
    }

    @Test
    @DisplayName("カテゴリリンクをホバーして正しい内容のカテゴリ一覧が表示されること")
    void categoryListIsCorrect(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();

        Locator categoryMenuLinks = page.locator(".sub-menu.js-menu > ul > li > a");
        assertThat(categoryMenuLinks).hasCount(6);

        String[] expectedCategoryNames = new String[] {"モバイルアプリケーション開発", "UX/UIデザイン", "Webアプリケーション開発", "先進技術研究", "新規事業開発", "その他のカテゴリ"};
        assertThat(categoryMenuLinks).hasText(expectedCategoryNames);
    }

    @Test
    @DisplayName("キーワード検索欄に文字を入力すると検索ボタンが活性化する。入力しないと非活性化される")
    void searchButtonBecomeSearchable(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();

        Locator searchButton = page.locator("main button:has-text(\"検索\")");
        String unsearchableCssClass = "js-search-button_search";
        String searchableCssClass = "js-search-button_search searchable";
        // 入力なしの場合、「class=js-search-button_search」、非活性化状態
        assertThat(searchButton).hasClass(unsearchableCssClass);
        assertThat(searchButton).not().hasClass(searchableCssClass);

        Locator searchTextBox = page.locator("main [placeholder=\"気になるキーワードをいれてください\"]");
        searchTextBox.click();
        searchTextBox.type("java");
        // 入力した場合、「class=js-search-button_search searchable」、活性化状態
        assertThat(searchButton).not().hasClass(unsearchableCssClass);
        assertThat(searchButton).hasClass(searchableCssClass);
    }

    @Test
    @DisplayName("キーワード検索ができる")
    void canSearchKeyword(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.searchKeyword("java");
    }
}
