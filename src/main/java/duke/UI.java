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

    private static String getGoodbyeMessage() {
        return (GOODBYE_MESSAGE);
    }
    public static String getGreeting() {
        return GREETING;
    }

    public static String start() {
        return getGreeting();
    }

    public static String end() {
        return getGoodbyeMessage();
    }
    
    /**
     * Prints current arraylist of tasks.
     *
     * @return string to display in GUI
     */
    public static String printList() {
        int len = TaskList.taskListLength();
        if (len < 1) {
            throw new DukeException("List is empty, you have no tasks!");
        }
        ArrayList<Task> taskArrayList = TaskList.getTaskList();
        int counter = 0;
        int numbering = 1;
        String printListString = "";

        while (counter < len) {
            Task temp = taskArrayList.get(counter);
            printListString += (numbering + "." + temp + "\n");
            counter++;
            numbering++;
        }
        return printListString;
    }

    public static String printAddition(Task task) {
        int tasksLeft = TaskList.taskListLength();
        return "Got it. I've added this task:\n " + " " + task.toString()
                + "\nNow you have " + tasksLeft + " tasks in the list.";
    }

    public static String markAsDoneUI(Task tsk) {
        return ("Nice! I've marked this task as done:\n" + "  " + tsk);
    }

    public static String markAsUndoneUI(Task tsk) {
        return "OK, I've marked this task as not done yet:\n" + "  " + tsk;
    }

    public static String deleteTaskUI(ArrayList<Task> arrayList, Task t) {
        return "Noted. I've removed this task:\n " + " " + t.toString()
                + "\nNow you have " + arrayList.size() + " tasks in the list.";
    }

    public static String findTasksUI(ArrayList<Task> arrayList) {
        int len = arrayList.size();
        if (len < 1) {
            throw new DukeException("No tasks with the given word!");
        }
        int counter = 0;
        int numbering = 1;
        String tasksFound = "";

        while (counter < len) {
            Task temp = arrayList.get(counter);
            tasksFound += (numbering + "." + temp + "\n");
            counter++;
            numbering++;
        }
        return String.format("Here are the matching tasks in your list:\n" + tasksFound);
    }

}
