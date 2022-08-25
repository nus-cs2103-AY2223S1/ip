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
    private static final String OPENING = "    Hello! I'm Duke\n    What can I do for you?\n    Type help for detailed useful guide.";
    private static final String ENDING = "    Bye. Hope to see you again soon!";
    private static final String ERROR_DEFAULT_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_PROCESS_MESSAGE = "OOPS!!! The command cannot be processed :( Type 'help' for more guides";
    private static final String ERROR_WRITE_MESSAGE = "OOPS!!! I fail to write in the file, possibly due to no permission. Please help me out :(";
    private static final String ERROR_UNFOUND_MESSAGE = "OOPS!!! There is no record matching your keyword :(";

    protected static void FormatPrint(String s) {
        System.out.println(SEPARATING_LINE);
        System.out.println(s);
        System.out.println(SEPARATING_LINE);
    }

    protected static void ListPrint(TaskList taskList) {
        ArrayList<Task> arr = taskList.taskArrayList;
        int count = 1;
        String result = "Here are the tasks in your list:\n";
        for (Task t : arr) {
            if (count != 1) {
                result += "\n";
            }
            result += "    " + String.valueOf(count) + ". " + t.toString();
            count++;
        }
        FormatPrint(result);
    }

    protected static void greet() {
        System.out.println("Hello from\n" + logo);
        FormatPrint(OPENING);
    }

    protected static void bye() {
        FormatPrint(ENDING);
    }

    protected static String readInput() {
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        return temp;
    }

    protected static void output(String arg) {
        System.out.println(arg);
    }

    protected static void processUnfoundResult() {
        Ui.FormatPrint(ERROR_UNFOUND_MESSAGE);
    }

    protected static void processExceptionOutput(String msg) {
        switch (msg) {
        case "Unable to parse query":
            // flow through
        case "Unable to process query":
            Ui.FormatPrint(ERROR_PROCESS_MESSAGE);
            break;
        case "Unable to write the record":
            Ui.FormatPrint(ERROR_WRITE_MESSAGE);
            break;
        default:
            Ui.FormatPrint(ERROR_DEFAULT_MESSAGE);
        }
    }
}
