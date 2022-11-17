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

    public void hasCorrectH1Tag(String categoryPath, String categoryName) {
        page.navigate(fintan.url("/blog-category/" + categoryPath + "/"));
        Locator h1Tag = page.locator("h1");
        assertThat(h1Tag).hasCount(1);
        assertThat(h1Tag).hasText(categoryName);
    }

    public void hasCorrectH2Tag(String categoryPath, String categoryName) {
        page.navigate(fintan.url("/blog-category/" + categoryPath + "/"));
        // 空のh2内容はJetpackが生成したもの（ほんとはJetpackの設定を見なし不要なh2を出入しないようにするが良いが影響範囲が判定されていないため暫定このまま）
        // https://ja.jetpack.com/support/carousel/
        Locator h1Tag = page.locator("h2");
        String[] expectedH2TagTexts = new String[] {categoryName, "", ""};
        assertThat(h1Tag).hasCount(expectedH2TagTexts.length);
        assertThat(h1Tag).containsText(expectedH2TagTexts);
    }
}
