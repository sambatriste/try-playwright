package com.example;

import com.example.pages.BlogDetailPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@ExtendWith(PlaywrightExtension.class)
public class BlogDetailPageTest {
    // 以下４つのページは固定ページのイメージだが、実際に記事ページになっている
    private static final String INQUIRY_PAGE_ID = "302";
    private static final String WHEN_USING_THIS_SITE_PAGE_ID = "303";
    private static final String ABOUT_TRADEMARK_PAGE_ID = "1622";
    private static final String LICENSE_PAGE_ID = "295";

    @Test
    @DisplayName("記事詳細ページのタイトルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        BlogDetailPage blogDetailPage = new BlogDetailPage(page);

        var idTitleMap = Map.of(
            "501", "VirtualCollaboBase",
            "163", "モバイルアプリケーションの配布",
            "1435", "Springアプリ開発ノウハウ集",
            INQUIRY_PAGE_ID, "お問い合わせ",
            WHEN_USING_THIS_SITE_PAGE_ID, "当サイトのご利用にあたって",
            ABOUT_TRADEMARK_PAGE_ID, "商標について",
            LICENSE_PAGE_ID, "ライセンス"

        );

        idTitleMap.forEach((id, title) -> blogDetailPage.navigate(id, title));
    }
}
