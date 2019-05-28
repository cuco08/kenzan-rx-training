package com.kenzan.reactive.training.homework.common.data;

import com.kenzan.reactive.training.homework.common.model.News;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.kenzan.reactive.training.homework.common.types.NewsType.*;

public class NewsData {
    private NewsData() {}

    public static List<News> getNews() {
        final List<News> newsList = new ArrayList<>();

        newsList.add(News.builder().id(1).type(SPORTS).description("Tigres defeats Leon").build());
        newsList.add(News.builder().id(2).type(SPORTS).description("Tuca gets mad").build());
        newsList.add(News.builder().id(3).type(POLITICS).description("AMLO hates media").build());
        newsList.add(News.builder().id(6).type(POLITICS).description("PEMEX politicians under investigation").build());
        newsList.add(News.builder().id(4).type(ENTERTAINMENT).description("Nobody liked the final season of GOT").build());
        newsList.add(News.builder().id(5).type(ENTERTAINMENT).description("Everybody wants to watch Chernobyl").build());

        Collections.shuffle(newsList);

        return newsList;
    }
}
