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
    // 以下五つのページは固定ページのイメージだが、実際に記事ページになっている
    private static final String INQUIRY_PAGE_ID = "302";
    private static final String WHEN_USING_THIS_SITE_PAGE_ID = "303";
    private static final String ABOUT_TRADEMARK_PAGE_ID = "1622";
    private static final String LICENSE_PAGE_ID = "295";
    private static final String FAQ_PAGE_ID = "299";

    private static final String META_DESCRIPTION = "Fintanは、TISインテックグループが研究開発や、システム開発、新規事業開発のプロジェクトで培ったノウハウを集約したサイトです。";

    @Test
    @DisplayName("記事詳細ページのタイトルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        BlogDetailPage blogDetailPage = new BlogDetailPage(page);

        Map<String, String> idTitleMap = Map.of(
            "501", "VirtualCollaboBase",
            "163", "モバイルアプリケーションの配布",
            "1435", "Springアプリ開発ノウハウ集",
            INQUIRY_PAGE_ID, "お問い合わせ",
            WHEN_USING_THIS_SITE_PAGE_ID, "当サイトのご利用にあたって",
            ABOUT_TRADEMARK_PAGE_ID, "商標について",
            LICENSE_PAGE_ID, "ライセンス",
            FAQ_PAGE_ID, "ＦＡＱ（よくあるご質問）"
        );

        idTitleMap.forEach((id, title) -> blogDetailPage.navigate(id, title));
    }

    @Test
    @DisplayName("metaタグのdescriptionが正しい内容であること")
    void hasCorrectMetaDescription(Page page) {
        BlogDetailPage blogDetailPage = new BlogDetailPage(page);

        Map<String, String> pageIdExcerptMap = Map.of(
            "501", "VR遠隔コラボレーションツールのベースとなるシステム～VirtualCollaboBaseを紹介します。",
            "163", "モバイルアプリケーションのテストにおけるテスト担当者へのアプリ配布方法・手順を、テストのユースケースごとに紹介しています。",
            "1435", "Springを活用してアプリケーションを構築する際に必要となるノウハウを提供します。特に、アプリケーションを開発する際に公式ドキュメントやWeb上から実装方法を見つけることが難しく、躓きやすい点についての情報を中心に提供します。",
            INQUIRY_PAGE_ID, "お問い合わせページです。" + META_DESCRIPTION,
            WHEN_USING_THIS_SITE_PAGE_ID, "当サイトのご利用についてはこちらのページをご覧ください。" + META_DESCRIPTION,
            ABOUT_TRADEMARK_PAGE_ID, "商標についてはこちらのページをご覧ください。" + META_DESCRIPTION,
            LICENSE_PAGE_ID, "ライセンスについてはこちらのページをご覧ください。" + META_DESCRIPTION,
            FAQ_PAGE_ID, "Fintanの利用についてのよくあるご質問をご紹介いたします。" + META_DESCRIPTION
        );

        pageIdExcerptMap.forEach((pageId, excerpt) -> blogDetailPage.hasCorrectMetaDescription(pageId, excerpt));
    }
}
