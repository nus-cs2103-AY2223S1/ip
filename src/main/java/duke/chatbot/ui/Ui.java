package duke.chatbot.ui;

import static duke.chatbot.common.Message.MESSAGE_INIT_FAILED;
import static duke.chatbot.common.Message.MESSAGE_SEPARATOR;
import static duke.chatbot.common.Message.MESSAGE_WELCOME;

import java.util.Scanner;

import duke.chatbot.command.CommandResult;

/**
 * Manages user input and also the output of the application.
 */
public class Ui {
    /** A scanner which takes in user input */
    private final Scanner userInput = new Scanner(System.in);

    /**
     * Prints a greeting message to the user.
     */
    public void greetUser() {
        System.out.println(MESSAGE_SEPARATOR);
        System.out.println(MESSAGE_WELCOME);
        System.out.println(MESSAGE_SEPARATOR);
    }

    /**
     * Prints a message that corresponds to the CommandResult argument.
     * @param result The result of a command to be outputted for
     *     the user to witness.
     */
    public void showMessage(CommandResult result) {
        System.out.println(MESSAGE_SEPARATOR);
        for (String line : result.getMessage()) {
            System.out.println("\t" + line);
        }
        System.out.println(MESSAGE_SEPARATOR);
    }

    /**
     * Prints an application initialisation error message.
     */
    public void printInitErrorMessage() {
        System.out.println(MESSAGE_INIT_FAILED);
    }

    /**
     * Returns a string that is obtained through user input.
     * @return A string obtained through user input.
     */
    public String getUserInput() {
        return userInput.nextLine();
    }
}
