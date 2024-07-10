package com.mr.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La strategia GenerationType.IDENTITY è quella più comunemente utilizzata per ottenere ID generati automaticamente dal database.
    @Column(name = "id")  // Se il nome della colonna è uguale a quello della variabile puoi anche scrivere solo @Column
    private int id;


    @Column(name = "total_price", precision = 9, scale = 2) // DECIMAL(9, 2)
    private BigDecimal totalPrice = BigDecimal.ZERO;


    // Con questo stabilisci una relazione 1 to 1
    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Con questo dici che la colonna user_id si riferisce alla colonna id della tabella users (entity User)
    private User user; // Qui verrà salvato l'intero user


    // Con questo indico una relazione MANY TO MANY con Article
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "cart_article", // Nome tabella ponte
            joinColumns = @JoinColumn(name="cart_id"), // Prende i Cart che hanno come ID la FK "cart_id"
            inverseJoinColumns = @JoinColumn(name="article_id") // Prende gli Article che hanno come ID la FK "article_id"
    )
    private Set<Article> articles;
}
