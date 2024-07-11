package com.mr.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La strategia GenerationType.IDENTITY è quella più comunemente utilizzata per ottenere ID generati automaticamente dal database.
    @Column(name = "id")  // Se il nome della colonna è uguale a quello della variabile puoi anche scrivere solo @Column
    private int id;

    @Column(name = "article_code", length = 8) // CHAR(8)
    private String articleCode;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "thumb")
    private String thumb;

    @Column(name = "price", precision = 9, scale = 2) // DECIMAL(9, 2)
    private BigDecimal price;

    @Column(name = "available_qta")
    private int availableQta;

    // Con questo indico una relazione inversa mappata in base all'attributo "articles" nel Cart.
    @ManyToMany(mappedBy = "articles")
    @JsonIgnore // Se usi i DTO e NON inserisci la variabile carts, puoi anche togliere questa annotazione
    private Set<Cart> carts;

    // Con questo indico una relazione inversa mappata in base all'attributo "articles" nel Order.
    @ManyToMany(mappedBy = "articles")
    @JsonIgnore
    private Set<Cart> orders;

    //TODO: Per la relazione one to meny con la tabella ponte
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartArticle> cartArticles;
}