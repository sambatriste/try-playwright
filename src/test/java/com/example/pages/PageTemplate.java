package com.example.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * ヘッダーとフッターを持つページテンプレートクラス。
 * 一般的なレイアウトのページに対応するPage Objectを作成する場合は、
 * 本クラスを継承するとよい。
 */
public abstract class PageTemplate {

    public final Fintan fintan = Fintan.getInstance();

    protected final Page page;

    public final Header header;

    public final Footer footer;

    public PageTemplate(Page page) {
        this.page = page;
        this.header = new Header(page);
        this.footer = new Footer(page);
    }

    /**
     * メタディスクリプションを取得する。
     * @return メタディスクリプション
     */
    public Locator getMetaDescription() {
        return page.locator("meta[name=description][content]");
    }
}
