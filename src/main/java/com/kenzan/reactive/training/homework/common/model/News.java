package com.kenzan.reactive.training.homework.common.model;

import com.kenzan.reactive.training.homework.common.types.NewsType;
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
