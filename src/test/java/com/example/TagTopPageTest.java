package com.example;

import com.example.pages.TagTopPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PlaywrightExtension.class)
public class TagTopPageTest {

    @Test
    @DisplayName("タグトップページのタイルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        TagTopPage tagTopPage = new TagTopPage(page);

        String[] tagNames = new String[] {"XR", "スクラム開発", "イベント"};

        for (String tagName: tagNames) {
            tagTopPage.navigate(tagName);
        }
    }
}
