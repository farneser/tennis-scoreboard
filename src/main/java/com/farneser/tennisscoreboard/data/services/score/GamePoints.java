package com.farneser.tennisscoreboard.data.services.score;

import lombok.Getter;

// a reference of another student's enum
@Getter
public enum GamePoints {

    LOVE("0"), FIFTEEN("15"), THIRTY("30"), FORTY("40"), ADVANTAGE("AD");

    private final String pointCode;

    GamePoints(String pointCode) {
        this.pointCode = pointCode;
    }

    public GamePoints next() {
        if (this == ADVANTAGE) {
            throw new IllegalStateException("Can't call next() on ADVANTAGE");
        } else {
            return GamePoints.values()[this.ordinal() + 1];
        }
    }

    @Override
    public String toString() {
        return pointCode;
    }
}
