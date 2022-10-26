package com.example;

import com.example.pages.CategoryListPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


@ExtendWith(PlaywrightExtension.class)
public class CategoryListPageTest {

    @Test
    @DisplayName("カテゴリ一覧に正しい内容のカテゴリ一覧が表示されること")
    void categoryListIsCorrect(Page page) {
        CategoryListPage categoryListPage = new CategoryListPage(page);
        categoryListPage.navigate();

        Locator categoryNameLinks = page.locator("ul.c-blog-category__list > li > .c-blog-category__block > a > h3");
        assertThat(categoryNameLinks).hasCount(18);

        String[] expectedCategoryNames = new String[] {
            "その他", "アジャイル・スクラム", "エンジニア育成・学習",
            "セキュリティ・暗号化", "ソフトウェアテスティング", "ブロックチェーン",
            "モバイルアプリケーション開発", "環境構築・ログ・CI/ CD", "量子コンピュータ",
            "開発プロセス", "要件定義", "Lerna",
            "Nablarch", "UX/UIデザイン", "Webアプリケーション開発",
            "先進技術研究", "新規事業開発", "活動発信・イベントレポート"
        };

        assertThat(categoryNameLinks).containsText(expectedCategoryNames);
    }

    @Test
    @DisplayName("「記事一覧へ」で該当カテゴリのカテゴリトップに遷移できること")
    void clickAuthorIcon(Page page) {
        CategoryListPage categoryListPage = new CategoryListPage(page);
        categoryListPage.navigate();

        // 「記事一覧へ」リンク
        Locator blogListLink = page.locator("ul.c-blog-category__list > li > .c-blog-category__block > a.link").first();
        // 遷移先URL
        String destinationUrl = blogListLink.getAttribute("href");

        blogListLink.click();
        assertThat(page).hasURL(destinationUrl);
    }
}
