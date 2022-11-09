package com.example;

import com.example.pages.SingleBlogPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

@ExtendWith(PlaywrightExtension.class)
public class SingleBlogPageTest {

    @Test
    @DisplayName("固定ページの各ページのタイトルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        SingleBlogPage singleBlogPage = new SingleBlogPage(page);

        var blogPathTitleMap = Map.of(
            "about", "私たちについて",
            "blog-category", "記事カテゴリ",
            "for-architect", "アーキテクト向けコンテンツ",
            "for-frontend-engineer", "フロントエンドエンジニア向けコンテンツ",
            "for-backend-engineer", "バックエンドエンジニア向けコンテンツ",
            "for-mobile-engineer", "モバイルエンジニア向けコンテンツ",
            "for-designer", "デザイナー向けコンテンツ",
            "new-grad", "職場・働き方紹介（学生向け）"

        );

        blogPathTitleMap.forEach((path, title) -> singleBlogPage.navigate(path, title));
    }
}
