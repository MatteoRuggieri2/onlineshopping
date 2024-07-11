package com.mr.onlineshopping.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ArticleQtaDTO {

    private int id;
    private String articleCode;
    private String name;
    private String description;
    private String thumb;
    private BigDecimal price;
    private int availableQta;
    private int selectedQta;
}
