package com.kenzan.reactive.training.homework.session6;

import rx.Observable;
import rx.Scheduler;

public class Schedulers {

    public static void testJust() {
        Observable.just("long", "longer", "longest")
                .map(String::length)
                .subscribe(length -> System.out.println("item length " + length + " " + Thread.currentThread()));
    }

    public static void testJustScheduler() throws Exception {
        final Scheduler scheduler = rx.schedulers.Schedulers.newThread();

        Observable.just("long", "longer", "longest")
                .map(String::length)
                .subscribeOn(scheduler)
                .subscribe(length -> System.out.println("item length " + length + " " + Thread.currentThread()));

        Thread.sleep(10000);
    }

    public static void testScheduler(Scheduler scheduler) throws Exception {
        Observable.just("long", "longer", "longest")
                .map(String::length)
                .subscribeOn(scheduler)
                .subscribe(length -> {
                    System.out.println("item length " + length + " " + Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {}
                });

        Thread.sleep(3000);
    }

    public static void testSchedulerFlatMap(Scheduler scheduler) throws Exception {
        /*Observable.just("long", "longer", "longest")
                .flatMap( o ->
                    Observable.just(o)
                            .map(String::length)
                            .subscribeOn(scheduler)
                            .subscribe(length -> {
                                System.out.println("item length " + length + " " + Thread.currentThread());
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {}
                            })
                );*/

        Thread.sleep(3000);
    }

    public static void main(String ... args) throws Exception {
        // Schedulers.testJust();
        // Schedulers.testJustScheduler();
        // Schedulers.testScheduler(Schedulers.newThread());
        // Schedulers.testScheduler(Schedulers.io());
        Schedulers.testSchedulerFlatMap(rx.schedulers.Schedulers.newThread());
    }
}
