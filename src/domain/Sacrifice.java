package domain;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Optional;

public class Sacrifice {

    private final Deque<BodyPart> bodyParts = new ArrayDeque<>(List.of(BodyPart.values()));

    public Optional<BodyPart> takeNextPart() {
        return Optional.ofNullable(bodyParts.pollFirst());
    }

    public boolean hasNextPart() {
        return !bodyParts.isEmpty();
    }
}
