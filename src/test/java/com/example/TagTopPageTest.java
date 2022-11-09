package com.example;

import com.example.pages.TagTopPage;
import com.example.playwright.PlaywrightExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(PlaywrightExtension.class)
public class TagTopPageTest {

    @Test
    @DisplayName("タグトップページのタイルが正しい内容であること")
    void hasCorrectTitle(Page page) {
        TagTopPage tagTopPage = new TagTopPage(page);

        var tagNames = List.of("XR", "スクラム開発", "イベント");
        tagNames.forEach(tagName -> tagTopPage.navigate(tagName));
    }
}
