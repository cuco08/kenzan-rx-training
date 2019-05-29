package com.kenzan.reactive.training.homework.session5.types;

import java.util.Arrays;

public enum Operation {
    PRINT, SUM, SUBTRACT;

    public static boolean isValidOperation(String op) {
        return Arrays.stream(Operation.values()).anyMatch(v -> op.toLowerCase().startsWith(v.name().toLowerCase()));
    }

    public static boolean isPrintOperation(String op) {
        return op.toLowerCase().startsWith(PRINT.name().toLowerCase());
    }

    public static boolean isSumOperation(String op) {
        return op.toLowerCase().startsWith(SUM.name().toLowerCase());
    }

    public static boolean isSubtractOperation(String op) {
        return op.toLowerCase().startsWith(SUBTRACT.name().toLowerCase());
    }
}
