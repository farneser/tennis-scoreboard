package com.farneser.tennisscoreboard.data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Players", indexes = {@Index(name = "nameIndex", columnList = "Name")})
@Data
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Player(String name) {
        this.name = name;
    }
}
