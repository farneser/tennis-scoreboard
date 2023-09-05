package com.farneser.tennisscoreboard.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Player", indexes = {@Index(name = "nameIndex", columnList = "Name")}, uniqueConstraints = {
        @UniqueConstraint(columnNames = "id")})
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
