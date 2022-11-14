package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * トップページ。
 *
 */
public class TopPage extends PageTemplate {

    public TopPage(Page page) {
        super(page);
    }

    public void setViewportSize(int width, int height) {
        page.setViewportSize(width, height);
    }

    /**
     * トップページに遷移する。
     */
    public void navigate() {
        page.navigate(fintan.url());
        Locator title = page.locator("title");
        // AIOSEOのプラグインにより、titleが複数になってしまうことがある
        assertThat(title).hasCount(1);
        assertThat(page).hasTitle("Fintan");
    }

    /**
     * 最新記事のカード一覧を取得する。
     * @return 最新記事のカード一覧（表示されているもののみ）
     */
    public Locator getLatestArticleCards() {
        return page.locator("section.c-top__latest.js-limit-latest > .o-inner > .o-card-list > .o-card:visible");
    }

    /**
     * 最新記事の「もっと見る」リンクをクリックする。
     */
    public void clickLatestArticleReadMore() {
        page.locator("text=もっと読む").first().click();
    }

    /**
     * 最新記事の「短く表示する」ボタンをクリックする。
     */
    public void clickDisplayLatestArticlesShortly() {
        Locator readMoreButton = page.locator("text=もっと読む").first();
        Locator cards = getLatestArticleCards();
        assertThat(cards).hasCount(8);

        // 毎回Clickで記事が8個増える
        readMoreButton.click();
        assertThat(cards).hasCount(16);

        readMoreButton.click();
        assertThat(cards).hasCount(24);

        // 記事が最大32個までに増える
        readMoreButton.click();
        assertThat(cards).hasCount(32);

        Locator displayShortlyButton = page.locator("text=短く表示する").first();
        // 32個まで増えたら「短く表示する」ボタンが出てくる
        assertThat(displayShortlyButton).isVisible();
        displayShortlyButton.click();
        assertThat(cards).hasCount(8);
    }

    public void searchKeyword(String keyword) {
        Locator searchTextBox = page.locator("main [placeholder=\"気になるキーワードをいれてください\"]");
        Locator searchButton = page.locator("main button:has-text(\"検索\")");

        searchTextBox.click();
        searchTextBox.type(keyword);
        searchButton.click();
        assertThat(page).hasURL(fintan.url("/?s=" + keyword));
    }
}
