package domain;

public class Gallows {

    private final Sacrifice sacrifice;

    private final HiddenWord hiddenWord;

    private final GameState gameState;

    public Gallows(Sacrifice sacrifice, HiddenWord hiddenWord) {
        this.sacrifice = sacrifice;
        this.hiddenWord = hiddenWord;
        gameState = new GameState(hiddenWord);
    }

    public GameState onLetterInput(char letter) {
        if (gameState.isFinished()) {
            return gameState;
        }
        boolean isLetterOpened = hiddenWord.tryOpenLetter(letter);
        if (hiddenWord.isWordRevealed()) {
            gameState.markAsWon();
        } else if (!isLetterOpened) {
            sacrifice.takeNextPart()
                    .ifPresent(gameState::addHangedPart);
            if (!sacrifice.hasNextPart()) {
                gameState.markAsLose();
            }
        }
        return gameState;
    }
}
