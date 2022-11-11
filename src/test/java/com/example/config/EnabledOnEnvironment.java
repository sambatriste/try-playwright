package com.example.config;


import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

/**
 * 環境によってテスト実行する/しないを切り替えるアノテーション。
 *
 * このアノテーションを付与したテストは、以下のいずれかの場合にだけ実行される。
 * <ul>
 * <li>「本番環境で動したい」かつ「実際に本番環境である」</li>
 * <li>「本番環境で動かさない」かつ「実際に本番環境でない」</li>
 * </ul>
 *
 * <pre>
 * | production指定 | 本番環境 | テスト実行 |
 * |----------------|----------|------------|
 * | true           | true     | される     |
 * | true           | false    | されない   |
 * | false          | true     | されない   |
 * | false          | false    | される     |
 * </pre>
 *
 * @see EnabledOnEnvironmentCondition
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ExtendWith(EnabledOnEnvironmentCondition.class)
public @interface EnabledOnEnvironment {
    /**
     * 本番環境で動作させるか。
     *
     * @return 本番環境で動作させる場合、真
     */
    boolean production();

    /**
     * 理由
     * @return 理由
     */
    String reason() default "";
}
