package repository;

import java.util.List;

public class InMemoryWordRepository implements WordRepository {

    private List<String> words = List.of(
            "солнце",
            "молоко",
            "машина",
            "гора",
            "солнце",
            "молоко",
            "машина",
            "гора",
            "зонт",
            "тигр",
            "книга",
            "окно",
            "музыка",
            "лампа",
            "кот",
            "ручка",
            "дерево",
            "море",
            "хлеб",
            "замок",
            "рыба",
            "мяч",
            "дождь"
    );

    @Override
    public String getRandomWord() {
        return words.get((int) (Math.random() * words.size()));
    }
}
