package com.kenzan.reactive.training.homework.session3.model;

import com.kenzan.reactive.training.homework.session3.types.NewsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News {
    private long id;

    private String description;

    private NewsType type;
}
