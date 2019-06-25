package com.kenzan.reactive.training.homework.session5.types;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

public enum OperationType {
    PRINT((x, y) -> x), SUM((x, y) -> x + y), SUBTRACT((x, y) -> x - y), MULTIPLY((x, y) -> x*y);

    private BiFunction<Integer, Integer, Integer> biFunction;

    OperationType(BiFunction<Integer, Integer, Integer> biFunction) {
        this.biFunction = biFunction;
    }

    public BiFunction<Integer, Integer, Integer> getBiFunction() {
        return biFunction;
    }

    public static boolean isValidOperation(String op) {
        return Arrays.stream(OperationType.values()).anyMatch(v -> getCommand(op).equalsIgnoreCase(v.name()));
    }

    public static boolean isValidMultiOperation(String command) {
        return Arrays.stream(command.split(" ")).allMatch(l ->
            Arrays.stream(OperationType.values()).anyMatch(v -> getCommand(l).equalsIgnoreCase(v.name()))
                || l.matches("-?\\d+")
        );
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

    public static Optional<OperationType> fromName(String name) {
        return Arrays.stream(OperationType.values()).filter(e -> e.name().equalsIgnoreCase(name)).findFirst();
    }
}
