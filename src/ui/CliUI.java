package ui;

import domain.BodyPart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CliUI implements UI<String> {

    private static final String GALLOWS_TEMPLATE = """
            |---------------------|
            |          |          |
            |          |          |
            |          H          |
            |       LH B RH       |
            |          B          |
            |       LL   RL       |
            |                     |
            |                     |
            |---------------------|
            |    G A L L O W S    |
            |_____________________|
            """;

    private static final String regularTemplate = """
            %s
            %s
            """;

    private static final String winTemplate = """
            %s
            %s
            Победа!
            """;

    private static final String lostTemplate = """
            %s
            Поражение, загаданное слово %s
            """;

    private final ConsoleInputReader reader = new ConsoleInputReader();

    @Override
    public void greetings() {
        System.out.println(
                """
                        В И С Е Л И Ц А !
                        """
        );
    }

    @Override
    public String showStartMenu() {
        System.out.println("1 - играть. 2 - выход");
        return reader.input();
    }

    @Override
    public String userInput() {
        System.out.print("Ввод одного символа: ");
        return reader.input();
    }

    @Override
    public void showWinResult(List<BodyPart> hangedParts, String hiddenWord) {
        System.out.println(
                winTemplate.formatted(renderGallows(hangedParts), hiddenWord)
        );
    }

    @Override
    public void showLostResult(List<BodyPart> hangedParts, String originWord) {
        System.out.println(
                lostTemplate.formatted(renderGallows(hangedParts), originWord)
        );
    }

    @Override
    public void showRegularResult(List<BodyPart> hangedParts, String hiddenWord) {
        System.out.println(
                regularTemplate.formatted(renderGallows(hangedParts), hiddenWord)
        );
    }

    @Override
    public void showIncorrectInput() {
        System.out.println("Некорректный ввод. Нужно ввести один символ");
    }

    @Override
    public void letterAlreadyUsed() {
        System.out.println("Символ уже был использован");
    }

    @Override
    public void shutDown() {
        reader.close();
    }

    private String renderGallows(List<BodyPart> hangedParts) {
        Map<String, String> wildcards = new HashMap<>(Map.of(
                " H ", "   ",
                "LH", "  ",
                "B", " ",
                "RH", "  ",
                "LL", "  ",
                "RL", "  "
        ));

        hangedParts.forEach(hangedPart -> {
            switch (hangedPart) {
                case HEAD -> wildcards.put(" H ", " 0 ");
                case LEFT_HAND -> wildcards.put("LH", " /");
                case BODY -> wildcards.put("B", "|");
                case RIGHT_HAND -> wildcards.put("RH", "\\ ");
                case LEFT_LEG -> wildcards.put("LL", " /");
                case RIGHT_LEG -> wildcards.put("RL", "\\ ");
            }
        });
        return wildcards.entrySet()
                .stream()
                .reduce(GALLOWS_TEMPLATE,
                        (template, wildcard) -> template.replace(wildcard.getKey(), wildcard.getValue()),
                        (previousTemplate, currentTemplate) -> currentTemplate);
    }
}
