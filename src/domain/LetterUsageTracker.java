package domain;

import java.util.HashSet;
import java.util.Set;

public class LetterUsageTracker {

    private final Set<Character> usedLetters = new HashSet<>();

    public boolean trackIfNotTracked(char letter) {
        char lowercaseLetter = Character.toLowerCase(letter);
        return usedLetters.add(lowercaseLetter);
    }
}
