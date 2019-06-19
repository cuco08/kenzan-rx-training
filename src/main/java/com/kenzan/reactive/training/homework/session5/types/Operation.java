package com.kenzan.reactive.training.homework.session5.types;

import java.util.Arrays;

public enum Operation {
    PRINT, SUM, SUBTRACT;

    public static boolean isValidOperation(String op) {
        return Arrays.stream(Operation.values()).anyMatch(v -> getCommand(op).equalsIgnoreCase(v.name()));
    }

    public static boolean isPrintOperation(String op) {
        return getCommand(op).equalsIgnoreCase(PRINT.name());
    }

    public static boolean isSumOperation(String op) {
        return getCommand(op).equalsIgnoreCase(SUM.name());
    }

    public static boolean isSubtractOperation(String op) {
        return getCommand(op).equalsIgnoreCase(SUBTRACT.name());
    }

    public static boolean isValidOperand(String ... operands) {
        boolean isValid = true;
        for (String op: operands) {
            try {
                Integer.valueOf(op);
            } catch (Exception e) {
                isValid = false;
            }
        }
        return isValid;
    }

    public static String[] getParts(String line) {
        return line.split(" ");
    }

    private static String getCommand(String line) {
        return getParts(line)[0];
    }
}
