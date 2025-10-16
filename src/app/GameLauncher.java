package app;

import domain.*;
import repository.InMemoryWordRepository;
import ui.CliUI;
import ui.UI;

public class GameLauncher {

    private static final String DECISION_START_GAME = "1";

    private static final String DECISION_EXIT_GAME = "2";

    private UI<String> ui = new CliUI();

    private Gallows gallows;

    private RussianLetterValidator russianLetterValidator;

    public void launch() {
        ui.greetings();
        String gameDecision;
        while (true) {
            gameDecision = ui.showStartMenu();
            if (gameDecision.equals(DECISION_START_GAME)) {
                init();
                startGame();
            } else if (gameDecision.equals(DECISION_EXIT_GAME)) {
                ui.shutDown();
                return;
            }
        }
    }

    private void init() {
        Sacrifice sacrifice = new Sacrifice();
        HiddenWord hiddenWord = new HiddenWord(new InMemoryWordRepository().getRandomWord(), new LetterUsageTracker());
        gallows = new Gallows(sacrifice, hiddenWord);
        russianLetterValidator = new RussianLetterValidator();
    }

    private void startGame() {
        boolean isGameInProgress = true;
        while (isGameInProgress) {
            GameState state = makePlayerMove();

            if (state.isGameWon()) {
                ui.showWinResult(state.getHangedParts(), state.getHiddenWord());
                isGameInProgress = false;
            }
            if (state.isGameLost()) {
                ui.showLostResult(state.getHangedParts(), state.getOriginalWord());
                isGameInProgress = false;
            }
            ui.showRegularResult(state.getHangedParts(), state.getHiddenWord());
        }
    }

    private GameState makePlayerMove() {
        while (true) {
            try {
                String userInput = ui.userInput();
                boolean isValid = russianLetterValidator.isValid(userInput);
                if (isValid) {
                    return gallows.onLetterInput(userInput.charAt(0));
                } else {
                    ui.showIncorrectInput();
                }
            } catch (LetterAlreadyUsedException e) {
                ui.letterAlreadyUsed();
            }
        }
    }
}
