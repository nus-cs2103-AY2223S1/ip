package duke.chatbot.ui;

import duke.chatbot.command.CommandResult;
import duke.chatbot.common.MessageConstants;

import java.util.Scanner;

public class Ui {
    private static final String MESSAGE_SEPARATOR = "\t____________________________________________________________";
    private final Scanner userInput = new Scanner(System.in);

    public void greetUser() {
        System.out.println(MESSAGE_SEPARATOR);
        System.out.println(MessageConstants.MESSAGE_WELCOME);
        System.out.println(MESSAGE_SEPARATOR);
    }

    public void showMessage(CommandResult result) {
        System.out.println(MESSAGE_SEPARATOR);
        for (String line : result.getMessage()) {
            System.out.println("\t" + line);
        }
        System.out.println(MESSAGE_SEPARATOR);
    }

    public void printInitErrorMessage() {
        System.out.println("An unexpected error has occured, try running again or call technical support!");
    }

    public String getUserInput() {
        return userInput.nextLine();
    }
}
