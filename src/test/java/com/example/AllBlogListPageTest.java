package com.example;

import com.example.pages.AllBlogListPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PlaywrightExtension.class)
public class AllBlogListPageTest {
    private static final String META_DESCRIPTION = "Fintanは、TISインテックグループが研究開発や、システム開発、新規事業開発のプロジェクトで培ったノウハウを集約したサイトです。";
    @Test
    @DisplayName("記事の総一覧リストページのタイトルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        AllBlogListPage allBlogListPage = new AllBlogListPage(page);
        allBlogListPage.navigate();
    }

    @Test
    @DisplayName("metaタグのdescriptionが正しい内容であること")
    void hasCorrectMetaDescription(Page page) {
        AllBlogListPage allBlogListPage = new AllBlogListPage(page);

        allBlogListPage.navigate();
        String expectMetaDescription = "記事の総一覧リストページです。" + META_DESCRIPTION;
        Locator metaDescription = page.locator("[name=description][content]");
        // AIOSEOのプラグインにより、descriptionが複数になってしまうことがある
        assertThat(metaDescription).hasCount(1);
        String actualMetaDescription = metaDescription.first().getAttribute("content");
        assertEquals(expectMetaDescription, actualMetaDescription);
    }
}
