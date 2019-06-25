package com.kenzan.reactive.training.homework.session5;

import com.kenzan.reactive.training.homework.session5.types.OperationType;
import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.Scanner;

public class Main {
    public static void main(String ... ar) {
        Scanner keyboard = new Scanner(System.in);
        ConnectableObservable cli = Observable.create(s -> {
            while (!s.isUnsubscribed()) {
                System.out.print("$");
                final String line = keyboard.nextLine();

                if (line.equalsIgnoreCase("bye")) {
                    s.unsubscribe();
                } else if (!OperationType.isValidOperation(line.toString())) {
                    System.out.println("Nothing to do. Try any of PRINT, SUM, SUBTRACT");
                } else {
                    s.onNext(line);
                }
            }
        }).publish();
        System.out.println("Say 'bye' to exit");

        cli.filter(line -> OperationType.isPrintOperation(line.toString())).subscribe(line ->
                System.out.println(line.toString().substring(OperationType.PRINT.name().length() + 1)));

        cli.filter(line -> OperationType.isSumOperation(line.toString())).subscribe(line -> {
            String[]parts = line.toString().split(" ");

            if (parts.length >= 3) {
                System.out.println(Integer.valueOf(parts[1]) + Integer.valueOf(parts[2]));
            }
        }, e -> System.out.println("Invalid input " + e));

        cli.filter(line -> OperationType.isSubtractOperation(line.toString())).subscribe(line -> {
            String[]parts = line.toString().split(" ");

            if (parts.length >= 3) {
                System.out.println(Integer.valueOf(parts[1]) - Integer.valueOf(parts[2]));
            }
        }, e -> System.out.println("Invalid input " + e));

        cli.connect();
    }
}
