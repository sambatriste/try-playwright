package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * 検索結果ページ。
 *
 */
public class SearchResultPage extends PageTemplate {

    public SearchResultPage(Page page) {
        super(page);
    }

    /**
     * 検索結果に遷移する。
     */
    public void navigate(String keyword) {
        page.navigate(fintan.url("/?s=" + keyword));
        Locator title = page.locator("title");
        // AIOSEOのプラグインにより、titleが複数になってしまうことがある
        assertThat(title).hasCount(1);
        assertThat(page).hasTitle("検索結果 | Fintan");
    }
}
