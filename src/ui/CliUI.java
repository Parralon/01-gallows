package ui;

import domain.BodyPart;

import java.util.List;

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
        String rendered = "";
        if (hangedParts.isEmpty()) {
            return GALLOWS_TEMPLATE.replace(" H ", "   ")
                    .replace("LH", "  ")
                    .replace("B", " ")
                    .replace("RH", "  ")
                    .replace("LL", "  ")
                    .replace("RL", "  ");
        }
        for (BodyPart hangedPart : hangedParts) {
            if (hangedPart == BodyPart.HEAD) rendered = GALLOWS_TEMPLATE.replace(" H ", " 0 ")
                    .replace("LH", "  ")
                    .replace("B", " ")
                    .replace("RH", "  ")
                    .replace("LL", "  ")
                    .replace("RL", "  ");
            if (hangedPart == BodyPart.LEFT_HAND) rendered = GALLOWS_TEMPLATE.replace(" H ", " 0 ")
                    .replace("LH", " /")
                    .replace("B", " ")
                    .replace("RH", "  ")
                    .replace("LL", "  ")
                    .replace("RL", "  ");
            if (hangedPart == BodyPart.BODY) rendered = GALLOWS_TEMPLATE.replace(" H ", " 0 ")
                    .replace("LH", " /")
                    .replace("B", "|")
                    .replace("RH", "  ")
                    .replace("LL", "  ")
                    .replace("RL", "  ");
            if (hangedPart == BodyPart.RIGHT_HAND) rendered = GALLOWS_TEMPLATE.replace(" H ", " 0 ")
                    .replace("LH", " /")
                    .replace("B", "|")
                    .replace("RH", "\\ ")
                    .replace("LL", "  ")
                    .replace("RL", "  ");
            if (hangedPart == BodyPart.LEFT_LEG) rendered = GALLOWS_TEMPLATE.replace(" H ", " 0 ")
                    .replace("LH", " /")
                    .replace("B", "|")
                    .replace("RH", "\\ ")
                    .replace("LL", " /")
                    .replace("RL", "  ");
            if (hangedPart == BodyPart.RIGHT_LEG) rendered = GALLOWS_TEMPLATE.replace(" H ", " 0 ")
                    .replace("LH", " /")
                    .replace("B", "|")
                    .replace("RH", "\\ ")
                    .replace("LL", " /")
                    .replace("RL", "\\ ");
        }
        return rendered;
    }
}
