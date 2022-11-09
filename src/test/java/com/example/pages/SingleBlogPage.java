package com.example.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * 固定ページの記事ページ。
 *
 */
public class SingleBlogPage extends PageTemplate {

    public SingleBlogPage(Page page) {
        super(page);
    }

    /**
     * 固定ページの記事ページに遷移する。
     */
    public void navigate(String blogPath, String blogTitle) {
        page.navigate(fintan.url("/" + blogPath + "/"));
        assertThat(page).hasTitle(blogTitle + " | Fintan");
    }
}
