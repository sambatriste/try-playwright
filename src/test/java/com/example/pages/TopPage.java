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
     * 最新記事の「もっと見る」リンクを取得する。
     */
    public Locator getLatestArticleReadMore() {
        return page.locator("text=もっと読む").nth(1);
    }

    /**
     * 最新記事の「短く表示する」リンクを取得する。
     */
    public Locator getLatestArticlesShortly() {
        return page.locator("text=短く表示する").nth(1);
    }


}
