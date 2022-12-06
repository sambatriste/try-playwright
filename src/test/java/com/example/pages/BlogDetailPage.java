package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.HashMap;
import java.util.Map;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 記事詳細ページ。
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

    public void navigate(String pageId) {
        page.navigate(fintan.url("/page/" + pageId + "/"));
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

    public void checkHeadingUniqueId(String pageId) {
        page.navigate(fintan.url("/page/" + pageId + "/"));
        Locator headings = page.locator("h2, h3, h4, h5");
        Map<String, Integer> counter = new HashMap<>();
        for (int i = 0; i < headings.count(); i++) {
            Locator heading = headings.nth(i);
            String name = heading.textContent();
            String id = heading.getAttribute("id");

            // 同じ名前の場合、末尾に_と数字を付与するため
            int current = counter.getOrDefault(name, 0);
            int next = current + 1;
            counter.put(name, next);
            if (current != 0) {
                name = name + "_" + next;
            }
            name = name.replaceAll("\\s|;/g", ""); // 空白を削除
            assertEquals(name, id, "count must be equal. pageId: [" + pageId + "]");
        }
    }
}
