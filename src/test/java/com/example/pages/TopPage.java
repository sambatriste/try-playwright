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

    /**
     * トップページに遷移する。
     */
    public void navigate() {
        page.navigate(fintan.url());
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

}
