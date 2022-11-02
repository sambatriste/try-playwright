package com.example.config;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.AnnotationUtils;

import java.lang.reflect.AnnotatedElement;

/**
 * {@link EnabledOnEnvironment}を判定する{@link ExecutionCondition}実装クラス。
 *
 * @see EnabledOnEnvironment
 */
public class EnabledOnEnvironmentCondition implements ExecutionCondition {

    private static final ConditionEvaluationResult ENABLED = ConditionEvaluationResult.enabled("environment match");

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        AnnotatedElement element = context.getElement().orElse(null);
        return AnnotationUtils.findAnnotation(element, EnabledOnEnvironment.class)
            .map(annotation -> {
                boolean expected = annotation.production();
                boolean actual = Configuration.isProductionEnvironment();
                return expected == actual ?
                    ENABLED:
                    disabled(annotation, actual);
            })
            .orElse(ENABLED);
    }

    private ConditionEvaluationResult disabled(EnabledOnEnvironment annotation, boolean isProduction) {
        return ConditionEvaluationResult.disabled(
            annotation + " is present. " +
                "production=[" + isProduction + "] " +
                "reason=[" + annotation.reason() + "]");
    }

}
