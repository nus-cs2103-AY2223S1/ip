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

    private static final String GREETING = "Hello! I'm Duke\n" +
            "What can I do for you?";

    private static final String GOODBYE_MESSAGE = "Bye, Hope to see you again soon!";

    private static void getLOGO() {
        System.out.println(LOGO);
    }

    public static void getLINE() {
        System.out.println(LINE);
    }

    private static void getGoodbyeMessage() {
        System.out.println(GOODBYE_MESSAGE);
    }
    private static void getGREETING() {
        System.out.println(GREETING);
    }

    public static void start() {
        getGREETING();
        getLINE();
    }

    public static void end() {
        getLINE();
        getGoodbyeMessage();
        getLINE();
    }


    //method to print in list format
    public static void printList() {
        int len = TaskList.taskListLength();
        if (len < 1) {
            throw new DukeException("List is empty, you have no tasks!");
        }
        ArrayList<Task> taskArrayList = TaskList.getTaskList();
        int counter = 0;
        int numbering = 1;
        getLINE();

        while (counter < len) {
            Task temp = taskArrayList.get(counter);
            System.out.println(numbering + "." + temp);
            counter++;
            numbering++;
        }
        getLINE();
    }

    public static void printAddition(Task task) {
        int tasksLeft = TaskList.taskListLength();
        getLINE();
        System.out.println("Got it. I've added this task:\n " + " " + task.toString() +
                "\nNow you have " + tasksLeft + " tasks in the list.");
        getLINE();
    }

    public static void markAsDoneUI(Task tsk) {
        getLINE();
        System.out.println("Nice! I've marked this task as done:\n" +
                "  " + tsk);
        getLINE();
    }

    public static void markAsUndoneUI(Task tsk) {
        getLINE();
        System.out.println("OK, I've marked this task as not done yet:\n" +
                "  " + tsk);
        getLINE();
    }

    public static void deleteTaskUI(ArrayList<Task> arrayList, Task t) {
        getLINE();
        System.out.println("Noted. I've removed this task:\n " + " " + t.toString() +
                "\nNow you have " + arrayList.size() + " tasks in the list.");
        getLINE();
    }

    public static void findTasksUI(ArrayList<Task> arrayList) {
        int len = arrayList.size();
        if (len < 1) {
            throw new DukeException("No tasks with the given word!");
        }

        int counter = 0;
        int numbering = 1;
        getLINE();

        System.out.println("Here are the matching tasks in your list:");
        while (counter < len) {
            Task temp = arrayList.get(counter);
            System.out.println(numbering + "." + temp);
            counter++;
            numbering++;
        }
        getLINE();
    }

}
