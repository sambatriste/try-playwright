package com.example;

import com.example.pages.TagTopPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(PlaywrightExtension.class)
public class TagTopPageTest {
    private static final String META_DESCRIPTION = "Fintanは、TISインテックグループが研究開発や、システム開発、新規事業開発のプロジェクトで培ったノウハウを集約したサイトです。";
    private final List<String> tagNames = List.of("XR", "スクラム開発", "イベント");
    @Test
    @DisplayName("タグトップページのタイトルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        TagTopPage tagTopPage = new TagTopPage(page);

        tagNames.forEach(tagName -> tagTopPage.navigate(tagName));
    }

    @Test
    @DisplayName("metaタグのdescriptionが正しい内容であること")
    void hasCorrectMetaDescription(Page page) {
        TagTopPage tagTopPage = new TagTopPage(page);

        tagNames.forEach(tagName -> {
            tagTopPage.navigate(tagName);
            String expectMetaDescription = "#" + tagName + "の記事一覧ページです。" + META_DESCRIPTION;
            String actualMetaDescription = page.locator("[name=description][content]").first().getAttribute("content");
            assertEquals(expectMetaDescription, actualMetaDescription);
        });
    }
}
