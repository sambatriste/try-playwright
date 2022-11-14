package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * 著者記事一覧ページ。
 *
 */
public class AuthorBlogListPage extends PageTemplate {

    public AuthorBlogListPage(Page page) {
        super(page);
    }

    /**
     * 著者記事一覧に遷移する。
     */
    public void navigate(String authorName) {
        page.navigate(fintan.url("/author/" + authorName + "/"));
        Locator title = page.locator("title");
        // AIOSEOのプラグインにより、titleが複数になってしまうことがある
        assertThat(title).hasCount(1);
        assertThat(page).hasTitle("著者：" + authorName + "の記事一覧 | Fintan");
    }
}
