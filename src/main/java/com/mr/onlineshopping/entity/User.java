package com.mr.onlineshopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La strategia GenerationType.IDENTITY è quella più comunemente utilizzata per ottenere ID generati automaticamente dal database.
    @Column(name = "id")  // Se il nome della colonna è uguale a quello della variabile puoi anche scrivere solo @Column
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    /* L'attributo "mappedBy" specifica il lato inverso della relazione.
    Indica che l'attributo user della classe Profile è quello che possiede la chiave esterna.
    In altre parole, dice a JPA che il campo user nell'entità Profile è responsabile
    della gestione della relazione. */
    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Cart cart;

    /* L'attributo "mappedBy" specifica il lato inverso della relazione. Indica che
     * l'attributo user della classe Order è quello che possiede la chiave esterna.
     * In altre parole, dice a JPA che il campo user nell'entità Order è
     * responsabile della gestione della relazione.
     * */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Aggiungilo per evitare il JSON infinito
    private List<Order> orders;
}
