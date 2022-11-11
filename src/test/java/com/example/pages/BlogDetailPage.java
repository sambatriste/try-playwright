package com.example.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    public void hasCorrectMetaDescription(String pageId, String expectMetaDescription) {
        page.navigate(fintan.url("/page/" + pageId + "/"));
        String actualMetaDescription = page.locator("[name=description][content]").first().getAttribute("content");
        assertEquals(expectMetaDescription, actualMetaDescription);
    }
}
