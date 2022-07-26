package com.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TopPage {

    private final Fintan fintan = new Fintan();
    private final Page page;

    public TopPage(Page page) {
        this.page = page;
    }

    public void navigate() {
        page.navigate(fintan.url());
        assertThat(page).hasTitle("Fintan");
    }

    public void clickAllPostListLink() {
        page.locator("text=記事の総一覧リスト").click();
        assertThat(page).hasURL(fintan.pageUrl());

    }

    public void clickSearchIcon() {
        page.locator(".o-header__search > a").click();
        Locator keywordInput = getKeywordInput();
        assertThat(keywordInput).isVisible();
    }

    public void searchKeyword(String keyword) {
        clickSearchIcon();

        Locator keywordInput = getKeywordInput();
        keywordInput.fill(keyword);
        keywordInput.click();
        page.locator("text=キーワードで探す 検索 >> button").click();
        assertThat(page).hasURL(fintan.url("/?s=" + keyword));
    }

    private Locator getKeywordInput() {
        return page.locator("text=キーワードで探す 検索 >> [placeholder=\"気になるキーワードをいれてください\"]");
    }
}
