package com.example.pages;

import com.microsoft.playwright.Page;

/**
 * ヘッダーとフッターを持つページテンプレートクラス。
 *
 * 一般的なレイアウトのページに対応するPage Objectを作成する場合は、
 * 本クラスを継承するとよい。
 */
public abstract class PageTemplate {

    protected final Fintan fintan = Fintan.getInstance();

    protected final Page page;

    public final Header header;

    public final Footer footer;

    public PageTemplate(Page page) {
        this.page = page;
        this.header = new Header(page);
        this.footer = new Footer(page);
    }
}
