package com.kenzan.reactive.training.homework.session3;

import com.kenzan.reactive.training.homework.session3.model.News;
import com.kenzan.reactive.training.homework.session3.subscriber.NewsSubscriber;
import com.kenzan.reactive.training.homework.session3.subscriber.NewsSubscriberHandler;
import rx.Observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kenzan.reactive.training.homework.session3.types.NewsType.SPORTS;
import static com.kenzan.reactive.training.homework.session3.types.NewsType.POLITICS;
import static com.kenzan.reactive.training.homework.session3.types.NewsType.ENTERTAINMENT;

public class Main {
    public static void main(String []args) {
        final List<News> newsList = new ArrayList<>();
        newsList.add(News.builder().id(1).description("Tigres defeats Leon").type(SPORTS).build());
        newsList.add(News.builder().id(2).description("Tuca gets mad").type(SPORTS).build());
        newsList.add(News.builder().id(3).description("AMLO hates media").type(POLITICS).build());
        newsList.add(News.builder().id(4).description("Nobody liked the final season of GOT").type(ENTERTAINMENT).build());
        newsList.add(News.builder().id(5).description("Everybody wants to watch Chernobyl").type(ENTERTAINMENT).build());
        newsList.add(News.builder().id(6).description("PEMEX politicians under investigation").type(POLITICS).build());

        final Observable<News> observable = Observable.from(newsList);
        NewsSubscriberHandler.subscribe(observable, NewsSubscriber.create("Subscriber1", Arrays.asList(SPORTS)));
        NewsSubscriberHandler.subscribe(observable, NewsSubscriber.create("Subscriber2", Arrays.asList(ENTERTAINMENT)));
        NewsSubscriberHandler.subscribe(observable, NewsSubscriber.create("Subscriber3", Arrays.asList(POLITICS, ENTERTAINMENT), Arrays.asList(ENTERTAINMENT)));
        NewsSubscriberHandler.subscribe(observable, NewsSubscriber.create("Subscriber4", Arrays.asList(SPORTS, ENTERTAINMENT)));
        NewsSubscriberHandler.subscribe(observable, NewsSubscriber.create("Subscriber5", Arrays.asList(SPORTS, ENTERTAINMENT, POLITICS)));
    }
}
