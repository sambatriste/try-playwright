package com.example.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    public void hasCorrectMetaDescription(String blogPath, String expectMetaDescription) {
        page.navigate(fintan.url("/" + blogPath + "/"));
        String actualMetaDescription = page.locator("[name=description][content]").first().getAttribute("content");
        assertEquals(expectMetaDescription, actualMetaDescription);
    }
}
