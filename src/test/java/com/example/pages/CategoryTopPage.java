package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    public void hasCorrectMetaDescription(String categoryPath, String expectMetaDescription) {
        page.navigate(fintan.url("/blog-category/" + categoryPath + "/"));
        Locator metaDescription = page.locator("[name=description][content]");
        // AIOSEOのプラグインにより、descriptionが複数になってしまうことがある
        assertThat(metaDescription).hasCount(1);
        String actualMetaDescription = metaDescription.first().getAttribute("content");
        assertEquals(expectMetaDescription, actualMetaDescription);
    }
}
