package duke.chatbot.ui;

import duke.chatbot.command.CommandResult;

import java.util.Scanner;

import static duke.chatbot.common.Message.MESSAGE_INIT_FAILED;
import static duke.chatbot.common.Message.MESSAGE_WELCOME;

public class Ui {
    private static final String MESSAGE_SEPARATOR = "\t____________________________________________________________";

    private final Scanner userInput = new Scanner(System.in);

    public void greetUser() {
        System.out.println(MESSAGE_SEPARATOR);
        System.out.println(MESSAGE_WELCOME);
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
        System.out.println(MESSAGE_INIT_FAILED);
    }

    public String getUserInput() {
        return userInput.nextLine();
    }
}
