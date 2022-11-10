package com.example;

import com.example.config.EnabledOnEnvironment;
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
    @DisplayName("カテゴリ一覧に正しい内容のカテゴリ一覧が表示されること（テスト環境）")
    @EnabledOnEnvironment(production = false, reason = "テスト環境のカテゴリが本番に追いついてない為")
    void categoryListIsCorrectTestEnv(Page page) {
        CategoryListPage categoryListPage = new CategoryListPage(page);
        categoryListPage.navigate();
        Locator categoryNameLinks = categoryListPage.getCategoryNameLinks();
        String[] expectedCategoryNames = new String[] {
            "その他", "アジャイル・スクラム", "エンジニア育成・学習",
            "セキュリティ・暗号化", "ソフトウェアテスティング", "ブロックチェーン",
            "モバイルアプリケーション開発", "環境構築・ログ・CI/ CD", "量子コンピュータ",
            "開発プロセス", "要件定義", "Lerna",
            "Nablarch", "UX/UIデザイン", "Webアプリケーション開発",
            "先進技術研究", "新規事業開発", "活動発信・イベントレポート"
        };
        assertThat(categoryNameLinks).hasCount(expectedCategoryNames.length);
        assertThat(categoryNameLinks).containsText(expectedCategoryNames);
    }

    @Test
    @DisplayName("カテゴリ一覧に正しい内容のカテゴリ一覧が表示されること（本番環境）")
    @EnabledOnEnvironment(production = true, reason = "テスト環境のカテゴリが本番に追いついてない為")
    void categoryListIsCorrectProductionEnv(Page page) {
        CategoryListPage categoryListPage = new CategoryListPage(page);
        categoryListPage.navigate();
        Locator categoryNameLinks = categoryListPage.getCategoryNameLinks();
        String[]  expectedCategoryNames = new String[] {
                "Webアプリケーション開発", "モバイルアプリケーション開発", "新規事業開発",
                "先進技術研究", "UX/UIデザイン", "XR",
                "ブロックチェーン", "Nablarch", "Lerna",
                "量子コンピュータ", "アジャイル・スクラム", "要件定義",
                "開発プロセス", "ソフトウェアテスティング", "環境構築・ログ・CI/ CD",
                "セキュリティ・暗号化", "エンジニア育成・学習", "活動発信・イベントレポート",
                "その他"
            };
        assertThat(categoryNameLinks).hasCount(expectedCategoryNames.length);
        assertThat(categoryNameLinks).containsText(expectedCategoryNames);
    }

    @Test
    @DisplayName("「記事一覧へ」で該当カテゴリのカテゴリトップに遷移できること")
    void clickAuthorIcon(Page page) {
        CategoryListPage categoryListPage = new CategoryListPage(page);
        categoryListPage.navigate();

        // 「記事一覧へ」リンク
        Locator blogListLink = categoryListPage.getBlogListLink();
        // 遷移先URL
        String destinationUrl = blogListLink.getAttribute("href");

        blogListLink.click();
        assertThat(page).hasURL(destinationUrl);
    }
}
