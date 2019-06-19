package com.kenzan.reactive.training.homework.session6;

import rx.subjects.BehaviorSubject;

/**
 * BehaviorSubject works this way: An observer, when subscribed to the BehaviorSubject, would get the last emitted
 * item before it subscribed and all subsequent items.
 */
public class BehaviourSubjects {
    public static void main(String ...args) {
        BehaviorSubject<Integer> bSubject = BehaviorSubject.create();

        bSubject.onNext(0);
        bSubject.onNext(1);

        bSubject.subscribe(it -> {
            System.out.println("Observer 1 onNext: " + it);
        }, (Throwable onError) -> {
        }, () -> System.out.println("Observer 1 onCompleted"));

        bSubject.onNext(2);
        bSubject.onNext(3);


        bSubject.subscribe(it -> {
            System.out.println("Observer 2 onNext: " + it);
        }, (Throwable onError) -> {
        }, () -> System.out.println("Observer 2 onCompleted"));

        bSubject.onNext(4);

        bSubject.subscribe(it -> {
            System.out.println("Observer 3 onNext: " + it);
        }, (Throwable onError) -> {
        }, () -> System.out.println("Observer 3 onCompleted"));

        bSubject.onNext(5);
        bSubject.onCompleted();
    }
}
