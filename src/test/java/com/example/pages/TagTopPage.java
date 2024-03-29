package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * タグトップページ。
 *
 */
public class TagTopPage extends PageTemplate {

    public TagTopPage(Page page) {
        super(page);
    }

    /**
     * タグトップに遷移する。
     */
    public void navigate(String tagName) {
        page.navigate(fintan.url("/blog-tag/" + tagName + "/"));
        Locator title = page.locator("title");
        // AIOSEOのプラグインにより、titleが複数になってしまうことがある
        assertThat(title).hasCount(1);
        assertThat(page).hasTitle("#" + tagName + "の記事一覧 | Fintan");
    }

    public void checkH1Tag(String tagName) {
        page.navigate(fintan.url("/blog-tag/" + tagName + "/"));
        Locator h1Tag = page.locator("h1");
        assertThat(h1Tag).hasCount(1);
        assertThat(h1Tag).hasText("#" + tagName + " のタグが付いた記事一覧");
    }

    /**
     * タグが付与された記事のタイトルを取得する。
     *
     * @return 記事のタイトル（記事の数だけ返却される）
     */
    public Locator getContentTitles() {
        return page.locator("div.o-card__title");
    }
}
