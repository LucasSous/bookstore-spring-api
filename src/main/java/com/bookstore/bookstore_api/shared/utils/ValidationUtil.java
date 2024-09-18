package com.bookstore.bookstore_api.shared.utils;

public class ValidationUtil {
    public static boolean isEmailValid(String email) {
        boolean isValid;
        isValid = !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        return isValid;
    }

    public static boolean isValueValid(String value) {
        boolean isValid;
        isValid = !value.matches("^[a-zA-Z]+(?: [a-zA-Z]+)*$");
        return isValid;
    }

    public static boolean hasSpaces(String value) {
        boolean hasSpaces;
        hasSpaces = value.matches(".*" + "\\s" + ".*");
        return hasSpaces;
    }
}
