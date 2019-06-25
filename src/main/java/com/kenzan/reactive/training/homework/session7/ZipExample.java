package com.kenzan.reactive.training.homework.session7;


import rx.Observable;
import rx.schedulers.Schedulers;

public class ZipExample {

    public static Observable getObservable(final long duration) {
        return Observable.fromCallable(() -> {
            System.out.println("Computing : " + duration);
            Thread.currentThread().sleep(duration);
            return Long.valueOf(duration / 10).intValue();
        });
    }


    public static void main(String ... args) throws Exception {
        Observable<Integer> observable1 = getObservable(300)
                .subscribeOn(Schedulers.newThread())
                .doOnCompleted(() -> System.out.println("Observable1 completed."));

        Observable<Integer> observable2 = getObservable(400)
                .subscribeOn(Schedulers.newThread())
                .doOnCompleted(() -> System.out.println("Observable2 completed."));

        Observable.zip(observable1, observable2, (x, y) -> x + y)
                .subscribe((result) -> System.out.println(result));

        Thread.sleep(2000);
    }
}
