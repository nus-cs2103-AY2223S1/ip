package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private String input;

    /**
     * Passes the user input to Duke.
     *
     * @return String representing user input.
     */
    public String getInput() {
        //return s.nextLine();
        return input;
    }

    /**
     * Changes the stored user input.
     *
     * @param input User input.
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Stops receiving user inputs.
     */
    public void end() {
        //s.close();
    }

    /**
     * Prints out Duke's greeting to the user.
     */
    public String greet() {
        String text;
        String logo = " ____                 \n"
                + "|  _ \\ _ _ _ __ _____ \n"
                + "| | | |  _  | |/ / _ \\\n"
                + "| |_| | |_| |   /  __/\n"
                + "|____/ \\__,_|\\_/ \\___|\n";
        text = "Hello! I'm\n" + logo;
        text = text + "What can I do for you?\n";
        return text;
    }

}
