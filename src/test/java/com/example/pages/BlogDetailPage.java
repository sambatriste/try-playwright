package com.example.pages;

import com.microsoft.playwright.Locator;
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
        Locator title = page.locator("title");
        // AIOSEOのプラグインにより、titleが複数になってしまうことがある
        assertThat(title).hasCount(1);
        assertThat(page).hasTitle(titleName + " | Fintan");
    }

    public void checkMetaDescription(String pageId, String expectMetaDescription) {
        page.navigate(fintan.url("/page/" + pageId + "/"));
        Locator metaDescription = getMetaDescription();
        // AIOSEOのプラグインにより、descriptionが複数になってしまうことがある
        assertThat(metaDescription).hasCount(1);
        String actualMetaDescription = metaDescription.first().getAttribute("content");
        assertEquals(expectMetaDescription, actualMetaDescription);
    }

    public void checkAuthorNameDomElement(String pageId, String expectedAuthorName) {
        page.navigate(fintan.url("/page/" + pageId + "/"));
        Locator authorDetail = page.locator(".post-author .info div a").first();
        assertThat(authorDetail).hasCount(1);
        assertEquals(expectedAuthorName, authorDetail.textContent());
    }

    public void checkTableOfContentsDomElement(String pageId, int tableOfContentsCount) {
        page.navigate(fintan.url("/page/" + pageId + "/"));
        Locator tableOfContents = page.locator("ol.post-table li");
        assertThat(tableOfContents).hasCount(tableOfContentsCount);
    }
}
