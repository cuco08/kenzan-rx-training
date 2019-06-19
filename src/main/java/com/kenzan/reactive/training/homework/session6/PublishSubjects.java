package com.kenzan.reactive.training.homework.session6;

import rx.subjects.PublishSubject;

/**
 * PublishSubject emits all the items at the point of subscription.
 */
public class PublishSubjects {
    public static void main(String ...args) {
        PublishSubject<Integer> pSubject = PublishSubject.create();

        pSubject.onNext(0);

        pSubject.subscribe(it -> {
            System.out.println("Observer 1 onNext: " + it);
        }, (Throwable onError) -> {
        }, () -> System.out.println("Observer 1 onCompleted"));

        pSubject.onNext(1);
        pSubject.onNext(2);


        pSubject.subscribe(it -> {
            System.out.println("Observer 2 onNext: " + it);
        }, (Throwable onError) -> {
        }, () -> System.out.println("Observer 2 onCompleted"));

        pSubject.onNext(3);

        pSubject.subscribe(it -> {
            System.out.println("Observer 3 onNext: " + it);
        }, (Throwable onError) -> {
        }, () -> System.out.println("Observer 3 onCompleted"));

        pSubject.onNext(4);
        pSubject.onCompleted();
    }
}
