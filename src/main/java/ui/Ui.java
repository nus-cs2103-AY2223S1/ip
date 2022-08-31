package ui;

import common.DukeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private final InputStreamReader inputStreamReader;
    private final BufferedReader bufferedReader;

    public Ui() {
        inputStreamReader = new InputStreamReader(System.in);
        bufferedReader = new BufferedReader(inputStreamReader);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    public void runExit() throws IOException {
        bufferedReader.close();
        inputStreamReader.close();
    }

    public void showLoadingError() {
        System.out.println("Duke is unable to load saved file");
    }

    public String readCommand() throws DukeException {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new DukeException("Duke is unable to read your input");
        }
    }

    public void showOutput(String output) {
        System.out.println(output);
    }
    

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLine() {
        System.out.println("-----------------------");
    }
}
