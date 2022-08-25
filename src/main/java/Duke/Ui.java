package Duke;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static final String initMessage = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke.Duke\n" +
            "     I can store a to-do list for you!\n" +
            "     Enter 'list' to display current contents and 'bye' to end the program.\n" +
            "     Start entering your tasks!\n" +
            "    ____________________________________________________________";


    private final Scanner in;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        System.out.println("Hello from\n" + logo);
        System.out.println(initMessage);
    }

    public String getUserInput() {
        return in.nextLine();
    }

    public void showGoodbyeMessage() {
        System.out.println(
                "    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");
    }


    public boolean hasInput() {
        return in.hasNext();
    }

    public void showEntry(String item) {
        System.out.println("    " + item);
    }

    public void showDeleteMessage(Task deletable, int size) {
        System.out.printf(
                "    ____________________________________________________________\n" +
                        "     Noted. I've removed this task:\n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________\n", deletable, size);
    }

    public void showInvalidTaskMessage() {
        System.out.println("Invalid task! Please input a number!");
    }

    public void showErrorReadingMessage() {
        System.out.println("Error reading from data!");
    }
    public void showInvalidIndexMessage() {
        System.out.println("Please Include a valid index!");
    }

    public void showErrorWritingMessage() {
        System.out.println("Error writing to data!");
    }

    public void showInvalidFindFiledMessage() {
        System.out.println("Please include a word to search for!");
    }
}