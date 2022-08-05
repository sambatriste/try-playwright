package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Header {

    private final Fintan fintan = Fintan.getInstance();

    private final Page page;

    public Header(Page page) {
        this.page = page;
    }

    /**
     * 検索アイコンをクリックする。
     * 検索パネルが表示される。
     */
    public void clickSearchIcon() {
        page.locator(".o-header__search > a").click();
        Locator keywordInput = getKeywordInput();
        assertThat(keywordInput).isVisible();
    }

    /**
     * キーワード検索を行う。
     * 入力したキーワードで検索し、検索画面が表示される。
     * @param keyword 入力するキーワード
     */
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
