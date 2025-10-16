package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputReader implements AutoCloseable {

    private final BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

    public String input() {
        try {
            return consoleReader.readLine();
        } catch (IOException e) {
            throw new UserInputException("TODO"); //TODO
        }
    }

    @Override
    public void close() {
        try {
            consoleReader.close();
        } catch (IOException ignore) {
        }
    }
}
