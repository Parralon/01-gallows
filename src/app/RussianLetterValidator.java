package app;

public class RussianLetterValidator {

    public boolean isValid(String input) {
        if (input.length() != 1) {
            return false;
        }
        char castedInputToChar = input.toLowerCase().charAt(0);
        return castedInputToChar >= 'а' && castedInputToChar <= 'я' || castedInputToChar == 'ё';
    }
}
