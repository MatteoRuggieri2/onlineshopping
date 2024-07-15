package com.mr.onlineshopping.entity;

import com.mr.onlineshopping.enums.OrderStatus;
import com.mr.onlineshopping.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La strategia GenerationType.IDENTITY è quella più comunemente utilizzata per ottenere ID generati automaticamente dal database.
    @Column(name = "id")  // Se il nome della colonna è uguale a quello della variabile puoi anche scrivere solo @Column
    private int id;

    @Column(name = "total_price", precision = 9, scale = 2) // DECIMAL(9, 2)
    private BigDecimal totalPrice;

    @Column(name = "payment_type")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private OrderStatus state;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_time")
    private LocalTime orderTime;


    @ManyToOne // Con questa indico la relazione inversa
    @JoinColumn(name = "user_id", nullable = false)  // Con questo sto dicendo di fare la join prendendo gli user che corrispondono a user_id
    private User user;

    // Con questo indico una relazione MANY TO MANY con Article
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_article", // Nome tabella ponte
            joinColumns = @JoinColumn(name="order_id"), // Prende gli Order che hanno come ID la FK "order_id"
            inverseJoinColumns = @JoinColumn(name="article_id") // Prende gli Article che hanno come ID la FK "article_id"
    )
    private List<Article> articles;

    //TODO: Per la relazione one to meny con la tabella ponte
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderArticle> orderArticles;
}
