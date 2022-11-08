package com.example;

import com.example.pages.SingleBlogPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(PlaywrightExtension.class)
public class SingleBlogPageTest {

    @Test
    @DisplayName("固定ページの各ページのタイルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        SingleBlogPage singleBlogPage = new SingleBlogPage(page);

        Map<String, String> blogPathTitleMap = new HashMap<>();

        blogPathTitleMap.put("about", "私たちについて");
        blogPathTitleMap.put("blog-category", "記事カテゴリ");
        blogPathTitleMap.put("for-architect", "アーキテクト向けコンテンツ");
        blogPathTitleMap.put("for-frontend-engineer", "フロントエンドエンジニア向けコンテンツ");
        blogPathTitleMap.put("for-backend-engineer", "バックエンドエンジニア向けコンテンツ");
        blogPathTitleMap.put("for-mobile-engineer", "モバイルエンジニア向けコンテンツ");
        blogPathTitleMap.put("for-designer", "デザイナー向けコンテンツ");
        blogPathTitleMap.put("new-grad", "職場・働き方紹介（学生向け）");

        for (Map.Entry<String, String> entry : blogPathTitleMap.entrySet()) {
            singleBlogPage.navigate(entry.getKey(), entry.getValue());
        }
    }
}
