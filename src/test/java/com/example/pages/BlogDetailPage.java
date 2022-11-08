package com.example.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * 記事詳細ページ。
 *
 */
public class BlogDetailPage extends PageTemplate {

    public BlogDetailPage(Page page) {
        super(page);
    }

    /**
     * 記事詳細に遷移する。
     */
    public void navigate(String pageId, String titleName) {
        page.navigate(fintan.url("/page/" + pageId + "/"));
        assertThat(page).hasTitle(titleName + " | Fintan");
    }
}
