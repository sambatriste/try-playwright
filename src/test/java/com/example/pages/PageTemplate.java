package com.example.pages;

import com.microsoft.playwright.Page;

abstract class PageTemplate {

    final Fintan fintan = Fintan.getInstance();

    final Page page;

    public final Header header;

    public final Footer footer;

    public PageTemplate(Page page) {
        this.page = page;
        this.header = new Header(page);
        this.footer = new Footer(page);
    }
}
