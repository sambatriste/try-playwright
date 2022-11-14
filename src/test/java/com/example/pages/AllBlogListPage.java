package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * 記事の総一覧リストページ。
 *
 */
public class AllBlogListPage extends PageTemplate {

    public AllBlogListPage(Page page) {
        super(page);
    }

    /**
     * 記事の総一覧リストに遷移する。
     */
    public void navigate() {
        page.navigate(fintan.url("/page/"));
        Locator title = page.locator("title");
        // AIOSEOのプラグインにより、titleが複数になってしまうことがある
        assertThat(title).hasCount(1);
        assertThat(page).hasTitle("記事の総一覧リスト | Fintan");
    }
}
