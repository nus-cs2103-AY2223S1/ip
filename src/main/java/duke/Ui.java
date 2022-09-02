package duke;

import java.util.Scanner;

public class Ui {

    private String input;
    private String output;

    /**
     * Display welcome message to user.
     */
    public String showWelcome() {
        String intro = "Hello! My name is GustavoBot, but you can call me Gus\n"
                + "How may I help you today?";

        return intro;
    }


    public String getInput() {
        return input;
    }

    /**
     * Stores input from user, to be parsed.
     */
    public void nextInput(String input) {
        this.input = input;
    }

    /**
     * Stores the output as response to user input.
     * @param output The output as a result of parsing user input.
     */
    public void nextOutput(String output) {
        this.output = output;
    }

    public String getOutput() {
        return output;
    }

    /**
     * Displays message to user.
     * @param message to be displayed.
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
