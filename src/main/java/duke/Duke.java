package duke;

import java.util.Scanner;

/**
 * Entry point of the Duke chatbot.
 * Starts the application and by initializing the driver class Dukebot
 * and handling passing input to it.
 */
public class Duke {

    public static void main(String[] args) throws DukeException {
        Dukebot driver = new Dukebot();

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            driver.handleInput(input);
        }
    }
}
