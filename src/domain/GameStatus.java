package domain;

public enum GameStatus {

    IN_PROGRESS,
    WON,
    LOST;

    public boolean isFinished() {
        return this == LOST || this == WON;
    }
}
