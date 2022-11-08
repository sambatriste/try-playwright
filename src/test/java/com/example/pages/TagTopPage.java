package com.example.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * タグトップページ。
 *
 */
public class TagTopPage extends PageTemplate {

    public TagTopPage(Page page) {
        super(page);
    }

    /**
     * タグトップに遷移する。
     */
    public void navigate(String tagName) {
        page.navigate(fintan.url("/blog-tag/" + tagName + "/"));
        assertThat(page).hasTitle("#" + tagName + "の記事一覧 | Fintan");
    }
}
