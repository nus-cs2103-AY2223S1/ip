package duke;

import java.util.ArrayList;
import java.util.Scanner;

class Ui {
    private static final String SEPARATING_LINE = "    ____________________________________________________________";
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String OPENING =
            "    Hello! I'm Duke\n    What can I do for you?\n    Type help for detailed useful guide.";
    private static final String ENDING = "    Bye. Hope to see you again soon!";
    private static final String ERROR_DEFAULT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_UNFOUND_MESSAGE = "OOPS!!! There is no record matching your keyword :(";
    private static final String ERROR_PROCESS_MESSAGE =
            "OOPS!!! The command cannot be processed :( Type 'help' for more guides";
    private static final String ERROR_WRITE_MESSAGE =
            "OOPS!!! I fail to write in the file, possibly due to no permission. Please help me out :(";

    /**
     * Prints the given string in a formatted manner.
     *
     * @param s string to be printed
     */
    protected static void formatPrint(String s) {
        System.out.println(SEPARATING_LINE);
        System.out.println(s);
        System.out.println(SEPARATING_LINE);
    }

    /**
     * Prints the list of tasks in a detailed manner.
     *
     * @param taskList the list of tasks
     */
    protected static void listPrint(TaskList taskList) {
        ArrayList<Task> arr = taskList.getTaskArrayList();
        int count = 1;
        String result = "Here are the tasks in your list:\n";
        for (Task t : arr) {
            if (count != 1) {
                result += "\n";
            }
            result += "    " + count + ". " + t.toString();
            count++;
        }
        Ui.formatPrint(result);
    }

    /**
     * Greets the user.
     */
    protected static void greet() {
        System.out.println("Hello from\n" + logo);
        Ui.formatPrint(OPENING);
    }

    /**
     * Says bye to the user.
     */
    protected static void bye() {
        Ui.formatPrint(ENDING);
    }

    /**
     * Reads the user input by one line.
     *
     * @return the user input in string
     */
    protected static String readInput() {
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        return temp;
    }

    /**
     * Outputs to the user with the given string.
     *
     * @param arg string to output
     */
    protected static void output(String arg) {
        System.out.println(arg);
    }

    protected static void processUnfoundResult() {
        Ui.formatPrint(ERROR_UNFOUND_MESSAGE);
    }

    protected static void taskStateChangePrint(Task t, boolean b) {
        String res;
        if (b) {
            res = "Nice! I've marked this task as done:\n";
        } else {
            res = "OK, I've marked this task as not done yet:\n";
        }
        Ui.formatPrint(res + t.toString());
    }

    /**
     * Processes the exception output.
     *
     * @param msg the error message to decide which scenario
     */
    protected static void processExceptionOutput(String msg) {
        switch (msg) {
        case "Unable to parse query":
            // flow through
        case "Unable to process query":
            Ui.formatPrint(ERROR_PROCESS_MESSAGE);
            break;
        case "Unable to write the record":
            Ui.formatPrint(ERROR_WRITE_MESSAGE);
            break;
        default:
            Ui.formatPrint(ERROR_DEFAULT_MESSAGE);
        }
    }
}
