package com.kenzan.reactive.training.homework.session7.cli;

import com.kenzan.reactive.training.homework.session5.types.OperationType;
import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MultiOperatorCli {

    public static Observable<Integer> compute(OperationType operationType, Stack< Observable<Integer> > operands) {
        while (operands.size() >= 2) {
            operands.push(Observable.zip(operands.pop(), operands.pop(), (x, y) ->
                    operationType.getBiFunction().apply(x, y)));
        }
        return operands.pop();
    }

    public static void main(String ... args) {
        Scanner keyboard = new Scanner(System.in);
        ConnectableObservable cli = Observable.create(s -> {
            while (!s.isUnsubscribed()) {
                System.out.print("$");
                final String line = keyboard.nextLine();

                if (line.equalsIgnoreCase("bye")) {
                    s.unsubscribe();
                } else if (!OperationType.isValidMultiOperation(line)) {
                    s.onError(new Throwable("Incorrect syntax. Enter one or more: [SUM|SUBTRACT|MULTIPLY follow by one or more integers]"));
                } else {
                    s.onNext(line);
                }
            }
        }).doOnError(e -> System.out.println(e.getMessage()))
                .retry()
                .publish();
        System.out.println("Say 'bye' to exit");

        cli.subscribe(line -> {
            final Stack< Observable<OperationType>> operations = new Stack<>();
            final Stack< Observable<Integer> > operands = new Stack<>();

            // Collect operations
            operations.addAll(
                Arrays.stream(line.toString().split(" "))
                    .filter(l -> OperationType.isValidOperation(l))
                    .map(op -> Observable.fromCallable(() -> OperationType.fromName(op).get()))
                    .collect(Collectors.toList())
            );

            // Compute operations
            final List<String> elements = Arrays.stream(line.toString().split(" ")).collect(Collectors.toList());
            IntStream.range(0, elements.size())
                    .mapToObj(index -> elements.get(elements.size() - index - 1))
                    .forEach(e -> {
                        if (e.matches("-?\\d+")) {
                            operands.push(Observable.just(Integer.valueOf(e)));
                        } else {
                            operations.pop().subscribe(type -> operands.push(compute(type, operands)));
                        }
                    });
            operands.pop().subscribe(r -> System.out.println(r));
        });

        cli.connect();
    }
}
