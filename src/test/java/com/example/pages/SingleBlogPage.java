package com.example.pages;

import com.microsoft.playwright.Locator;
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
        Locator title = page.locator("title");
        // AIOSEOのプラグインにより、titleが複数になってしまうことがある
        assertThat(title).hasCount(1);
        assertThat(page).hasTitle(blogTitle + " | Fintan");
    }

    public void checkMetaDescription(String blogPath, String expectMetaDescription) {
        page.navigate(fintan.url("/" + blogPath + "/"));
        Locator metaDescription = page.locator("[name=description][content]");
        // AIOSEOのプラグインにより、descriptionが複数になってしまうことがある
        assertThat(metaDescription).hasCount(1);
        String actualMetaDescription = metaDescription.first().getAttribute("content");
        assertEquals(expectMetaDescription, actualMetaDescription);
    }

    public void checkH1Tag(String blogPath, String blogTitle) {
        page.navigate(fintan.url("/" + blogPath + "/"));
        Locator h1Tag = page.locator("h1");
        assertThat(h1Tag).hasCount(1);
        assertThat(h1Tag).hasText(blogTitle);
    }
}
