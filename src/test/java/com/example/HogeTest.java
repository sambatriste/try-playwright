package com.example;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HogeTest {

    @Test
    @DisplayName("aa")
    void aaaa() {
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
//                    .setHeadless(false));
//            BrowserContext context = browser.newContext();
//            // Open new page
//            Page page = context.newPage();
//            // Go to https://fintan.jp/
//            page.navigate("https://fintan.jp/");
//            // Click .o-header__search > a
//            page.locator(".o-header__search > a").click();
//            // Click [placeholder="気になるキーワードをいれてください"]
//            Locator input = page.locator("[placeholder=\"気になるキーワードをいれてください\"]");
//            input.click();
//            // Fill [placeholder="気になるキーワードをいれてください"]
//
//            input.fill("java");
//            input.press("Tab");
//
//            // Click button:has-text("検索")
//            Locator searchButton = page.locator("button:has-text(\"検索\")");
//            assertThat(searchButton).isEnabled();
//            searchButton.click();
//
//
//            assertThat(page).hasURL("https://fintan.jp/?s=java");
//        }
    }
}
