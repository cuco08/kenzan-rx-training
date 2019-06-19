package com.kenzan.reactive.training.homework.session6;

import com.kenzan.reactive.training.homework.session5.types.Operation;
import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.Scanner;

public class ReactiveCli {

    public static void main(String ... args) {
        Scanner keyboard = new Scanner(System.in);
        ConnectableObservable cli = Observable.create(s -> {
            while (!s.isUnsubscribed()) {
                System.out.print("$");
                final String line = keyboard.nextLine();

                if (line.equalsIgnoreCase("bye")) {
                    s.unsubscribe();
                } else if (!Operation.isValidOperation(line)) {
                    s.onError(new Throwable("Nothing to do. Try any of PRINT, SUM, SUBTRACT"));
                } else {
                    s.onNext(line);
                }
            }
        }).doOnError(e -> System.out.println(e.getMessage()))
                .retry()
                .publish();
        System.out.println("Say 'bye' to exit");

        cli.filter(line -> Operation.isPrintOperation(line.toString())).subscribe(line ->
                System.out.println(line.toString().substring(Operation.PRINT.name().length()).trim()));

        cli.filter(line -> Operation.isSumOperation(line.toString())).subscribe(line -> {
            String[]parts = Operation.getParts(line.toString());

            if (parts.length == 3 && Operation.isValidOperand(parts[1], parts[2])) {
                System.out.println(Integer.valueOf(parts[1]) + Integer.valueOf(parts[2]));
            } else {
                System.out.println("Invalid input. Please try: SUM n1 n2 where n1 and n2 are integers.");
            }
        }, e -> System.out.println(((Throwable)e).getMessage()));

        cli.filter(line -> Operation.isSubtractOperation(line.toString())).subscribe(line -> {
            String[]parts = Operation.getParts(line.toString());

            if (parts.length == 3 && Operation.isValidOperand(parts[1], parts[2])) {
                System.out.println(Integer.valueOf(parts[1]) - Integer.valueOf(parts[2]));
            } else {
                System.out.println("Invalid input. Please try: SUBTRACT n1 n2 where n1 and n2 are integers.");
            }
        }, e -> System.out.println(((Throwable)e).getMessage()));

        cli.connect();
    }
}
