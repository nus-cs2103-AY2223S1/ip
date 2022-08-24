package duke;

import java.util.ArrayList;
import java.util.Scanner;

class Ui {
    static final String SEPARATING_LINE = "    ____________________________________________________________";
    static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static final String OPENING = "    Hello! I'm Duke\n    What can I do for you?\n    Type help for detailed useful guide.";
    static final String ENDING = "    Bye. Hope to see you again soon!";

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
}
