package duke;

import duke.task.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * UI of the application Inspired by AddressBook
 */

public class Ui {
    private final BufferedReader in;
    private final PrintStream out;

    public Ui() {
        this(new InputStreamReader(System.in), System.out);
    }

    public Ui(InputStreamReader in, PrintStream out) {
        this.in = new BufferedReader(in);
        this.out = out;
    }

    public String readCommand() {
        String input = "";
        try {
            input = in.readLine();
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void greet() {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        this.out.println("Hello from\n" + logo);
    }

    public void exitMessage() {
        this.printMessage("Bye. Hope to see you again soon!");
    }

    public void printMessage(String[] strArray) {
        this.out.println("_______________________________________________________");
        this.out.println("\tHere are the tasks in your list:");
        for (String str : strArray) {
            this.out.println("\t" + str);
        }
        this.out.println("_______________________________________________________");
    }

    public void printMessage(String str) {
        this.out.println("_______________________________________________________" + "\n\t" + str + "\n"
                + "_______________________________________________________");
    }

    public String wrapMessage(String str, String taskDescription, TaskList taskList) {
        return String.format(
                str + "\n\t\t" + taskDescription + "\n\tNow you have " + taskList.size() + " tasks in the list.");
    }
}
