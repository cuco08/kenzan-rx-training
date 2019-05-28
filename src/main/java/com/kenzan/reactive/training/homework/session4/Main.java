package com.kenzan.reactive.training.homework.session4;

import com.kenzan.reactive.training.homework.common.data.NewsData;
import com.kenzan.reactive.training.homework.common.data.SubscriberData;
import com.kenzan.reactive.training.homework.common.model.News;
import com.kenzan.reactive.training.homework.common.subscriber.NewsSubscriber;
import com.kenzan.reactive.training.homework.common.subscriber.NewsSubscriberHandler;
import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.List;

public class Main {
    public static void main(String []args) {
        final Observable<News> observable = Observable.from(NewsData.getNews());
        final ConnectableObservable<News> connectableObservable = observable.publish();
        final List<NewsSubscriber> subscriberList = SubscriberData.getNewsSubscribers();

        NewsSubscriberHandler.subscribe(connectableObservable, subscriberList);
        connectableObservable.connect();
    }
}
