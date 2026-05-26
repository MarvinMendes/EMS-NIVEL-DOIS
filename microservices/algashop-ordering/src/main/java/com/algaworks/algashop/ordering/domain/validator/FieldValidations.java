package com.algaworks.algashop.ordering.domain.validator;

import org.apache.commons.validator.routines.EmailValidator;
import java.util.Objects;

public final class FieldValidations {

    private static final String DEFAULT_NON_BLANK_MESSAGE = "value must not be null or blank";
    private static final String DEFAULT_INVALID_EMAIL_MESSAGE = "invalid email";

    private FieldValidations() {
        throw new AssertionError("Utility class");
    }

    public static String requiresNonBlank(String value) {
        return requiresNonBlank(value, null);
    }

    public static String requiresNonBlank(String value, String errorMessage) {
        String msg = resolveMessage(errorMessage, DEFAULT_NON_BLANK_MESSAGE);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(msg);
        }
        return value;
    }

    public static String requiresValidEmail(String email) {
        return requiresValidEmail(email, null);
    }

    public static String requiresValidEmail(String email, String errorMessage) {
        String msg = resolveMessage(errorMessage, DEFAULT_INVALID_EMAIL_MESSAGE);
        requiresNonBlank(email, msg);
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new IllegalArgumentException(msg);
        }
        return email;
    }

    private static String resolveMessage(String custom, String defaultMsg) {
        if (custom == null || custom.isBlank()) {
            return defaultMsg;
        }
        return custom;
    }
}
