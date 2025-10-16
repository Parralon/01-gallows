package domain;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private GameStatus gameStatus;
    private final HiddenWord hiddenWord;
    private final List<BodyPart> hangedParts;

    public GameState(HiddenWord hiddenWord) {
        this.hiddenWord = hiddenWord;
        gameStatus = GameStatus.IN_PROGRESS;
        hangedParts = new ArrayList<>();
    }

    public boolean isFinished() {
        return gameStatus.isFinished();
    }

    public boolean isGameLost() {
        return gameStatus == GameStatus.LOST;
    }

    public boolean isGameWon() {
        return gameStatus == GameStatus.WON;
    }

    public String getHiddenWord() {
        return hiddenWord.getMaskedWord();
    }

    public String getOriginalWord() {
        if (gameStatus.isFinished()) {
            return hiddenWord.getOriginalWord();
        }
        throw new IllegalStateException("Нет доступа к открытому слову пока игра не закончена");
    }

    public List<BodyPart> getHangedParts() {
        return List.copyOf(hangedParts);
    }

    void markAsWon() {
        gameStatus = GameStatus.WON;
    }

    void markAsLose() {
        gameStatus = GameStatus.LOST;
    }

    void addHangedPart(BodyPart bodyPart) {
        hangedParts.add(bodyPart);
    }
}
