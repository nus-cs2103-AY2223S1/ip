package duke;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

/**
 * Main class of the program
 * Stores a taskList of tasks
 */
public class Duke {

    private static TaskList tasks;
    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";




    public Duke() {
        tasks = Storage.load();
    }


    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Displays the welcome message.
     * Initializes the scanner to scan for inputs
     * Lets the parser parse the correct input
     * Terminate the program if the user requests for it
     */
    public static void run() {
        Ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                Ui.displayMessage(ENDING_MESSAGE);
                break;
            }
            //if not, parser can parse data
            Parser.parseData(input, tasks);
        }
    }


}

