package com.kenzan.reactive.training.homework.session3.subscriber;

import com.kenzan.reactive.training.homework.session3.model.News;
import rx.Observable;

public class NewsSubscriberHandler {
    private NewsSubscriberHandler() {}

    public static void subscribe(Observable<News> observable, NewsSubscriber subscriber) {
        if (observable != null && subscriber != null) {
            observable
                    .filter(e -> subscriber.getAllowedTypes().contains(e.getType()))
                    .subscribe(subscriber);
        }
    }
}
