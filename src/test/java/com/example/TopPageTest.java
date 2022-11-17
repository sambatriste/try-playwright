package com.example;

import com.example.pages.TopPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PlaywrightExtension.class)
public class TopPageTest {

    private static final String FINTAN_PROD_URL = "https://fintan.jp";
    private static final String META_DESCRIPTION = "Fintanは、TISインテックグループが研究開発や、システム開発、新規事業開発のプロジェクトで培ったノウハウを集約したサイトです。研究成果や、PJ推進のプラクティス、要件定義/設計/プログラミング/テストといった作業のプラクティス、成果物のテンプレート/サンプル、各種開発ツールを提供します。Fintanは、どなたでも無償でご利用いただけます。";

    @Test
    @DisplayName("トップページからカテゴリ一覧ページに遷移できること")
    void clickCategoryLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickCategoryLink();
    }

    @Test
    @DisplayName("ヘッダから「私たちについて」をクリックしに画面遷移できること")
    void clickAboutUsLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickAboutUsLink();
    }

    @Test
    @DisplayName("トップページから記事一覧に遷移できること")
    void clickAllPostListLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.footer.clickAllPostListLink();
    }

    @Test
    @DisplayName("虫眼鏡から検索ウィンドウを開けること")
    void searchPanelOpen(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickSearchIcon();
    }

    @Test
    @DisplayName("虫眼鏡から検索ウィンドウを閉じれること")
    void searchPanelClose(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickToCloseSearchPanel();
    }

    @Test
    @DisplayName("横幅<=500px未満でハンバーガーメニューが出ること")
    void displayHamburgerMenu(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        Locator hamburgerMenu = page.locator(".o-hamburger.js-hamburger");
        // デフォルトサイズが1280*720でハンバーガーメニューが出ない
        assertThat(hamburgerMenu).not().isVisible();

        topPage.setViewportSize(500, 1280);
        assertThat(hamburgerMenu).isVisible();

        // 横幅>500pxでハンバーガーメニューが出ない
        topPage.setViewportSize(501, 1280);
        assertThat(hamburgerMenu).not().isVisible();
    }

    @Test
    @DisplayName("検索窓を表示した状態でハンバーガーメニューの表示と非表示を切り替えられること")
    void displayHamburgerMenuWithOpeningSearchPanel(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickSearchIcon();
        Locator hamburgerMenu = page.locator(".o-hamburger.js-hamburger");
        // デフォルトサイズが1280*720でハンバーガーメニューが出ない
        assertThat(hamburgerMenu).not().isVisible();

        topPage.setViewportSize(500, 1280);
        assertThat(hamburgerMenu).isVisible();
    }

    @Test
    @DisplayName("下にスクロールした時ヘッダが上部に出ない。上にスクロールした時ヘッダが上部に出ること")
    void notDisplayHeaderWhenScrollDown(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        Locator header = page.locator("header");
        assertThat(header).hasClass("o-header");
        // ページを下方向に200ピクセルをスクロールする
        page.mouse().wheel(0, 200);

        assertThat(header).hasClass("o-header fix-over");
        // ページを上方向に100ピクセルをスクロールする
        page.mouse().wheel(0, -100);
        assertThat(header).hasClass("o-header");

        page.mouse().wheel(0, -100);
        assertThat(header).hasClass("o-header state-top");
    }

    @Test
    @DisplayName("記事リストの表示型が「タイル型とリスト型の間」に切り替えられること")
    void changeBetweenTileAndListDisplay(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        // 初期表示時、リスト型表示
        Locator listButton = page.locator(".o-card-switch__button.o-card-switch__button--list").first();
        assertThat(listButton).hasClass("o-card-switch__button o-card-switch__button--list js-view-switch_list is_active");

        Locator tileButton = page.locator(".o-card-switch__button.o-card-switch__button--icon").first();
        assertThat(tileButton).hasClass("o-card-switch__button o-card-switch__button--icon js-view-switch_icon");

        Locator blogList = page.locator("section.c-top__latest.js-limit-latest > .o-inner > .o-card-list");
        assertThat(blogList).hasClass("o-card-list o-card-list--list-view");

        // タイル表示ボタン押下後、タイル型表示となる
        tileButton.click();
        assertThat(listButton).hasClass("o-card-switch__button o-card-switch__button--list js-view-switch_list");
        assertThat(tileButton).hasClass("o-card-switch__button o-card-switch__button--icon js-view-switch_icon is_active");
        assertThat(blogList).hasClass("o-card-list");

        // リスト表示ボタン押下後、リスト表示に戻る
        listButton.click();
        assertThat(listButton).hasClass("o-card-switch__button o-card-switch__button--list js-view-switch_list is_active");
        assertThat(tileButton).hasClass("o-card-switch__button o-card-switch__button--icon js-view-switch_icon");
        assertThat(blogList).hasClass("o-card-list o-card-list--list-view");
    }

    @Test
    @DisplayName("最新記事の「もっと見る」をクリックすると記事が追加で表示されること")
    void readMore(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();

        Locator cards = topPage.getLatestArticleCards();
        assertThat(cards).hasCount(8);

        topPage.clickLatestArticleReadMore();
        assertThat(cards).hasCount(16);
    }

    @Test
    @DisplayName("最新記事の「短く表示する」をクリックすると記事が追加で表示されること")
    void displayBlogsShortly(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.clickDisplayLatestArticlesShortly();
    }

    @Test
    @DisplayName("最新記事リストの記事にメインカテゴリ、タイトル、抜粋内容、タグ、著者アイコン、著者名、日付、いいね数が表示されていること")
    void blogDetailsArelVisible(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        // メインカテゴリ
        assertThat(page.locator("li.o-card > .o-card__content > a >.o-card__head > .o-card__category").first()).isVisible();
        // タイトル
        assertThat(page.locator("li.o-card > .o-card__content > a >.o-card__title").first()).isVisible();
        // 抜粋内容
        assertThat(page.locator("li.o-card > .o-card__content > a >.o-card__text").first()).isVisible();
        // タグ
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__tag").first()).isVisible();
        // 著者アイコン
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__user > .image").first()).isVisible();
        // 著者名
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__user > .info > h5").first()).isVisible();
        // 日付
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__user > .info > .bottom > time").first()).isVisible();
        // いいね数
        assertThat(page.locator("li.o-card > .o-card__content > .o-card__user > .info > .bottom > .like").first()).isVisible();
    }

    @Test
    @DisplayName("最新記事リストの記事から一つの著者アイコンを押下し著者ページに遷移できること")
    void clickAuthorIcon(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();

        // 著者アイコン
        Locator authorIcon = page.locator("li.o-card > .o-card__content > .o-card__user > .image").first();
        authorIcon.click();
        // 遷移先URL
        String destinationUrl = page.locator("li.o-card > .o-card__content > .o-card__user > .image > a").first().getAttribute("href");
        assertThat(page).hasURL(destinationUrl);
    }

    @Test
    @DisplayName("リンク押下で該当記事に遷移できること")
    void clickBlogLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        Locator blogLink = page.locator("li.o-card > .o-card__content > a").first();
        String destinationUrl = blogLink.getAttribute("href");
        blogLink.click();
        assertThat(page).hasURL(destinationUrl);
    }

    @Test
    @DisplayName("Fintanロゴを押下しトップページに遷移できること")
    void clickFintanLogo(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.clickFintanLogo();
    }

    @Test
    @DisplayName("カテゴリリンクをホバーしてカテゴリ一覧が表示されること")
    void hoverCategoryLink(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.header.hoverCategoryLink();
    }

    @Test
    @DisplayName("カテゴリリンクをホバーして正しい内容のカテゴリ一覧が表示されること")
    void categoryListIsCorrect(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();

        Locator categoryMenuLinks = page.locator(".sub-menu.js-menu > ul > li > a");
        assertThat(categoryMenuLinks).hasCount(6);

        String[] expectedCategoryNames = new String[] {"モバイルアプリケーション開発", "UX/UIデザイン", "Webアプリケーション開発", "先進技術研究", "新規事業開発", "その他のカテゴリ"};
        if (topPage.fintan.url().equals(FINTAN_PROD_URL)) {
            expectedCategoryNames = new String[] {"Webアプリケーション開発", "モバイルアプリケーション開発", "新規事業開発", "先進技術研究", "UX/UIデザイン", "その他のカテゴリ"};
        }
        assertThat(categoryMenuLinks).hasText(expectedCategoryNames);
    }

    @Test
    @DisplayName("キーワード検索欄に文字を入力すると検索ボタンが活性化する。入力しないと非活性化されること")
    void searchButtonBecomeSearchable(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();

        Locator searchButton = page.locator("main button:has-text(\"検索\")");
        String unsearchableCssClass = "js-search-button_search";
        String searchableCssClass = "js-search-button_search searchable";
        // 入力なしの場合、「class=js-search-button_search」、非活性化状態
        assertThat(searchButton).hasClass(unsearchableCssClass);
        assertThat(searchButton).not().hasClass(searchableCssClass);

        Locator searchTextBox = page.locator("main [placeholder=\"気になるキーワードをいれてください\"]");
        searchTextBox.click();
        searchTextBox.type("java");
        // 入力した場合、「class=js-search-button_search searchable」、活性化状態
        assertThat(searchButton).not().hasClass(unsearchableCssClass);
        assertThat(searchButton).hasClass(searchableCssClass);
    }

    @Test
    @DisplayName("キーワード検索ができること")
    void canSearchKeyword(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        topPage.searchKeyword("java");
    }

    @Test
    @DisplayName("metaタグのdescriptionが正しい内容であること")
    void checkMetaDescription(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        Locator metaDescription = page.locator("[name=description][content]");
        // AIOSEOのプラグインにより、descriptionが複数になってしまうことがある
        assertThat(metaDescription).hasCount(1);
        String actualMetaDescription = metaDescription.first().getAttribute("content");
        assertEquals(META_DESCRIPTION, actualMetaDescription);
    }

    @Test
    @DisplayName("h2タグが正しい内容であること")
    void checkH2Tag(Page page) {
        TopPage topPage = new TopPage(page);
        topPage.navigate();
        Locator h2Tags = page.locator("h2");
        // 空のh2内容はJetpackが生成したもの（ほんとはJetpackの設定を見なし不要なh2を出入しないようにするが良いが影響範囲が判定されていないため暫定このまま）
        // https://ja.jetpack.com/support/carousel/
        String[] h2TagTexts = new String[] {"Fintanとは", "キーワードでさがす", "おすすめ記事", "最新記事", "人気記事", "お知らせ", "", ""};
        assertThat(h2Tags).hasCount(h2TagTexts.length);
        assertThat(h2Tags).containsText(h2TagTexts);
    }
}
