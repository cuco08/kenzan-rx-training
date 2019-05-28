package com.kenzan.reactive.training.homework.common.subscriber;

import com.kenzan.reactive.training.homework.common.model.News;
import com.kenzan.reactive.training.homework.common.types.NewsType;
import lombok.Getter;
import rx.Subscriber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class NewsSubscriber extends Subscriber<News> {
    private String name;

    private List<NewsType> allowedTypes = new ArrayList<>();

    private List<NewsType> rejectedTypes = new ArrayList<>();

    private NewsSubscriber(String name, List<NewsType> allowedTypes) {
        this.name = name;
        this.allowedTypes.addAll(allowedTypes);
    }

    private NewsSubscriber(String name, List<NewsType> allowedTypes, List<NewsType> rejectedTypes) {
        this.name = name;
        this.allowedTypes.addAll(allowedTypes);
        this.rejectedTypes.addAll(rejectedTypes);
    }

    public static NewsSubscriber create(String name, NewsType ... allowedTypes) {
        return new NewsSubscriber(name, Arrays.asList(allowedTypes));
    }

    public static NewsSubscriber create(String name, List<NewsType> allowedTypes, List<NewsType> rejectedTypes) {
        return new NewsSubscriber(name, allowedTypes, rejectedTypes);
    }

    public void onCompleted() {
        System.out.println(this.name + " has received all their news.");
    }

    public void onError(Throwable throwable) {
        System.out.println("There was a failure trying to submit some news to " + this.name);
    }

    public void onNext(News n) {
        if (rejectedTypes.contains(n.getType())) {
            System.out.println(this.name + " is unsubscribing as he/she received news about [" + n.getType() + "]");
            this.unsubscribe();
        } else {
            System.out.println("[" + this.name + " : " + n.getType() + "] " + n.getDescription());
        }
    }
}
