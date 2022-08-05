## コード生成

ブラウザを人力で操作することで、その操作からコードを生成できる。

Test Generator  
https://playwright.dev/java/docs/codegen

``` sh
mvn exec:java@codegen-with-proxy
```

プロキシ下で実行する場合は環境変数`HTTPS_PROXY`を設定しておく。

``` sh
set HTTPS_PROXY=http://proxy.example.com:8080
mvn exec:java@codegen-with-proxy
```

## テスト実行時の環境設定

プロジェクト直下に`.env.local`ファイルを作成する。
サンプルが置いてあるのそれを参考にする。

このファイルはバージョン管理されない。自身の環境にあった値を記入しておく。

設定値はシステムプロパティ、環境変数で上書きできる。


## テスト実行時のオプション

### ブラウザの選択


`playwright.browser-type`で指定できる。


Java版では自分でブラウザを指定する必要があり、Node.js版のような自動でパラレルに各ブラウザでテストする仕組みはない。

テストを起動するプロセス毎に、どのブラウザでテストするかパラメータ指定することで、
テストコードを変更することなくブラウザを変えてテストできる。

``` sh
set PLAYWRIGHT_BROWSER_TYPE=firefox && mvn test
```

