package duke;

import java.util.ArrayList;

public class UI {
    private static final String LINE = "____________________________________________________________";

    private static final String LOGO = "Hello from\n" +
            " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String MESSAGE_LOGO = "Hello from\n" + LOGO;

    private static final String GREETING = "Hello! I'm Duke :D\n" +
            "What can I do for you?";

    private static final String GOODBYE_MESSAGE = "Bye, Hope to see you again soon!";

    private static String getLOGO() {
        return (LOGO);
    }

    private static String getLine() {
        return LINE;
    }

    private static String getGoodbyeMessage() {
        return (GOODBYE_MESSAGE);
    }
    public static String getGREETING() {
        return GREETING;
    }

    public static String start() {
        return getGREETING();
        //getLine();
    }

    public static String end() {
        //getLine();
        return getGoodbyeMessage();
        //getLine();
    }


    //method to print in list format
    public static String printList() {
        int len = TaskList.taskListLength();
        if (len < 1) {
            throw new DukeException("List is empty, you have no tasks!");
        }
        ArrayList<Task> taskArrayList = TaskList.getTaskList();
        int counter = 0;
        int numbering = 1;
        //getLine();
        String printListString = "";

        while (counter < len) {
            Task temp = taskArrayList.get(counter);
            printListString += (numbering + "." + temp + "\n");
            counter++;
            numbering++;
        }
        return printListString;
        //getLine();
    }

    public static String printAddition(Task task) {
        int tasksLeft = TaskList.taskListLength();
        //getLine();
        return "Got it. I've added this task:\n " + " " + task.toString() +
                "\nNow you have " + tasksLeft + " tasks in the list.";
        //getLine();
    }

    public static String markAsDoneUI(Task tsk) {
        //getLine();
        return ("Nice! I've marked this task as done:\n" +
                "  " + tsk);
        //getLine();
    }

    public static String markAsUndoneUI(Task tsk) {
        //getLine();
        return "OK, I've marked this task as not done yet:\n" +
                "  " + tsk;
        //getLine();
    }

    public static String deleteTaskUI(ArrayList<Task> arrayList, Task t) {
        //getLine();
        return "Noted. I've removed this task:\n " + " " + t.toString() +
                "\nNow you have " + arrayList.size() + " tasks in the list.";
        //getLine();
    }

    public static String findTasksUI(ArrayList<Task> arrayList) {
        int len = arrayList.size();
        if (len < 1) {
            throw new DukeException("No tasks with the given word!");
        }
        int counter = 0;
        int numbering = 1;
        //getLine();
        String tasksFound = "";

        while (counter < len) {
            Task temp = arrayList.get(counter);
            tasksFound += (numbering + "." + temp + "\n");
            counter++;
            numbering++;
        }
        //getLine();
        return String.format("Here are the matching tasks in your list:\n" + tasksFound);
    }

}
