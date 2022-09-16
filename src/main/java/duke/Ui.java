package duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Contains user interface elements and logic for Duke
 */
public class Ui {
    public static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    public static final String INSTRUCTIONS = "To see all tasks type list\nTo add a todo, type todo <taskname>\n"
            + "To add a deadline, type deadline <description> /by <datetime string>"
            + "\nTo add an event, type event <description> /at <datetime string>"
            + "\nTo mark a task as done, type mark <task number>"
            + "\nTo mark a task as undone, type unmark <task number>"
            + "\nTo delete a task, type delete <task number>"
            + "\nTo search for task descriptions, type find <query>";
    private final Scanner scanner;
    private final PrintStream out;

    Ui(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    private boolean shouldIgnoreLine(String line) {
        return line.trim().isEmpty() || line.contains("//");
    }
}
