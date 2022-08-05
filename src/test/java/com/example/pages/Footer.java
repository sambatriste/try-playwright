package com.example.pages;

import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Footer {

    private final Fintan fintan = Fintan.getInstance();

    private final Page page;

    public Footer(Page page) {
        this.page = page;
    }

    /**
     * 記事の総一覧リストのリンクをクリックする。
     * /page/に遷移する。
     */
    public void clickAllPostListLink() {
        page.locator("text=記事の総一覧リスト").click();
        assertThat(page).hasURL(fintan.pageUrl());

    }
}
