package com.kenzan.reactive.training.homework.session2;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main (String ...args) throws Exception {
        final String[] items = new String[]{"one", "two", null, "three"};
        final Observable observable =  Observable.from(items);

        final Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("[Observer] is done.");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[Observer] error occurred: " + throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("[Observer] Item :: " + s);
            }
        };

        final Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("[Subscriber] is done.");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[Subscriber] error occurred: " + throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("[Subscriber] Item :: " + s + " length " + s.length());
            }
        };

        observable.subscribe(observer);
        observable.subscribe(subscriber);

        Observable intervalObservable = Observable.interval(1, TimeUnit.SECONDS);
        intervalObservable.subscribe(System.out::println);
        Action1 action1 = System.out::println;
        //Thread.sleep(5000);
    }
}
