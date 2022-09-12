package jenny.util;

import jenny.tasks.TaskList;

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
    private Printer pr;

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
     * Set Ui to use the provided PrintStream for sending outputs.
     *
     * @param out the PrintStream to use.
     */
    public void setPrintStream(PrintStream out) {
        this.pr = new Printer(out);
    }

    /**
     * Sends a greeting to the output stream.
     */
    public void greet() {
        print(new String[]{
                "Hello! I'm Jenny",
                "What can I do for you Forest?"
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
        print("Bye Bye Forest!");
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

    /**
     * Converts the given TaskList into a String and formats it to send a series of messages to the output stream.
     *
     * @param tasks an {@link TaskList} of {@link jenny.tasks.Task}.
     */
    public void print(TaskList tasks) {
        String tempStr = tasks.toString();
        int tempStrLen = tempStr.length();
        tempStr = tempStr.substring(1, tempStrLen - 1);
        String[] messages = tempStr.split(", ");
        pr.print(messages);
    }
}
