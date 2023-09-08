package com.farneser.tennisscoreboard.data.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1", nullable = false)
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2", nullable = false)
    private Player player2;

    @ManyToOne
    @JoinColumn(name = "winner", nullable = false)
    private Player winner;

    public Match(Player player1, Player player2, Player winner) {
        if (player1 == null || player2 == null || winner == null) {
            throw new IllegalArgumentException("Player1, Player2, and Winner must not be null.");
        }

        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    // Переопределение equals и hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id == match.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
