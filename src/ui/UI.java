package ui;

import domain.BodyPart;

import java.util.List;

public interface UI<T> {

    void greetings();

    T showStartMenu();

    T userInput();

    void showWinResult(List<BodyPart> hangedParts, String hiddenWord);

    void showLostResult(List<BodyPart> hangedParts, String originWord);

    void showRegularResult(List<BodyPart> hangedParts, String hiddenWord);

    void showIncorrectInput();

    void letterAlreadyUsed();

    void shutDown();
}
