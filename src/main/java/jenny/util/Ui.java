package jenny.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interaction with the user.
 * CS2103 Week 3
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public final class Ui {
    private final Scanner sc;
    private final Printer pr;

    /**
     * Creates a new instance of user interface to interact with the user.
     *
     * @param in  an {@link InputStream}
     * @param out an {@link OutputStream}
     */
    public Ui(InputStream in, PrintStream out) {
        sc = new Scanner(in);
        pr = new Printer(out);
    }

    /**
     * Sends a greeting to the output stream.
     */
    public void greet() {
        print(new String[]{
            "Hello! I'm JennyBot",
            "What can I do for you?"
        });
    }

    /**
     * Read and return a line from the input stream if present,
     * return "" otherwise.
     *
     * @return a string from the input stream if present, "" otherwise.
     */
    public String read() {
        return (sc.hasNextLine() ? sc.nextLine() : "");
    }

    /**
     * Sends an exit message to the output stream.
     */
    public void exit() {
        print("Bye. Hope to see you again soon!");
    }

    /**
     * Sends a message to the output stream.
     *
     * @param message a message.
     */
    public void print(String message) {
        pr.print(new String[]{message});
    }

    /**
     * Sends a series of messages to the output stream.
     *
     * @param messages an array of {@link String}.
     */
    public void print(String[] messages) {
        pr.print(messages);
    }

    /**
     * Sends a series of messages to the output stream.
     *
     * @param messages an {@link ArrayList} of {@link String}.
     */
    public void print(ArrayList<String> messages) {
        pr.print(messages);
    }
}
