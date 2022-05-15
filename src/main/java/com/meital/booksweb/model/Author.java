package com.meital.booksweb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "authors")
@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 40)
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> books;

}
