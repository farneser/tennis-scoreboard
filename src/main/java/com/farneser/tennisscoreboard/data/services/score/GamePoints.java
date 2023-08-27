package com.farneser.tennisscoreboard.data.services.score;

import lombok.Getter;

// a reference of another student's enum
@Getter
public enum GamePoints {

    Zero("0"), Fifteen("15"), Thirty("30"), Forty("40"), Advantage("AD");

    public GamePoints next() {
        if (this == Advantage) {
            throw new IllegalStateException("Can't call next() on ADVANTAGE");
        } else {
            return GamePoints.values()[this.ordinal() + 1];
        }
    }

    private final String pointCode;

    GamePoints(String pointCode) {
        this.pointCode = pointCode;
    }

}
