package com.example.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * カテゴリ一トップページ。
 *
 */
public class CategoryTopPage extends PageTemplate {

    public CategoryTopPage(Page page) {
        super(page);
    }

    /**
     * カテゴリ一トップに遷移する。
     */
    public void navigate(String categoryPath, String categoryName) {
        page.navigate(fintan.url("/blog-category/" + categoryPath + "/"));
        assertThat(page).hasTitle(categoryName + " | Fintan");
    }
}
