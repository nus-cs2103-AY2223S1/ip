package blob.ui;

import blob.common.Messages;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Text UI of the Blob task manager application
 */
public class TextUi {
    /** Divider to separate message instances by the Blob text UI */
    private static final String MESSAGE_DIVIDER = "=".repeat(100);

    /** Header to signify the start of a message from the Blob text UI to the user */
    private static final String MESSAGE_HEADER = "\u001B[33m" + "Blob says: " + "\u001B[0m";

    /** Prompt to alert user for input */
    private static final String USER_PROMPT = ">> ";

    Scanner in;
    PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    private TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input should be ignored by the application.
     * Inputs should be ignored if they are empty or whitespaces.
     *
     * @param userInput the user input to check for
     * @return true if user input should be input
     */
    private boolean shouldIgnoreInput(String userInput) {
        return userInput.trim().isEmpty();
    }

    /**
     * Prints the user prompt to prompt user for input
     */
    public void promptUserInput() {
        out.print(USER_PROMPT);
    }

    /**
     * Prints the greeting message to the user.
     */
    public void greetUser() {
        speakToUser(Messages.MESSAGE_WELCOME_1, Messages.MESSAGE_WELCOME_2);
    }

    /**
     * Prints the goodbye message to the user.
     */
    public void sayGoodbyeToUser() {
        speakToUser(Messages.MESSAGE_GOODBYE_1, Messages.MESSAGE_GOODBYE_2);
    }


    /**
     * Prints a sequence of strings to the user, encapsulated in a message instance.
     *
     * @param content the sequence of strings to be printed
     */
    public void speakToUser(String ...content) {
        System.out.println("\n" + MESSAGE_DIVIDER);
        System.out.println(MESSAGE_HEADER);
        for (int i = 0; i < content.length; i++) {
            System.out.println("\t" + content[i]);
        }
        System.out.println(MESSAGE_DIVIDER + "\n");
    }
}
