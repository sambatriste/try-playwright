package com.example.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * カテゴリ一覧ページ。
 *
 */
public class CategoryListPage extends PageTemplate {

    public CategoryListPage(Page page) {
        super(page);
    }

    /**
     * カテゴリ一覧に遷移する。
     */
    public void navigate() {
        page.navigate(fintan.url("/blog-category/"));
        assertThat(page).hasTitle("記事カテゴリ | Fintan");
    }
}
