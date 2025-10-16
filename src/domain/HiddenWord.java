package domain;

import java.util.stream.IntStream;

public class HiddenWord {

    private final String maskSymbol = "*";

    private final String originalWord;

    private final StringBuilder maskedWord;

    private final LetterUsageTracker letterUsageTracker;

    public HiddenWord(String originalWord, LetterUsageTracker letterUsageTracker) {
        this.originalWord = originalWord;
        this.letterUsageTracker = letterUsageTracker;
        maskedWord = new StringBuilder(maskSymbol.repeat(originalWord.length()));
    }

    public boolean tryOpenLetter(char letter) {
        if (!letterUsageTracker.trackIfNotTracked(letter)) {
            throw new LetterAlreadyUsedException();
        }
        if (!originalWord.contains(Character.toString(letter)) || isWordRevealed()) {
            return false;
        }

        IntStream.range(0, originalWord.length())
                .filter(letterIndex -> originalWord.charAt(letterIndex) == letter)
                .forEach(letterIndex -> maskedWord.setCharAt(letterIndex, letter));
        return true;
    }

    public boolean isWordRevealed() {
        return maskedWord.indexOf(maskSymbol) == -1;
    }

    public String getMaskedWord() {
        return maskedWord.toString();
    }

    public String getOriginalWord() {
        return originalWord;
    }
}
