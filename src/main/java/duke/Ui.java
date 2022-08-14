package duke;

import java.util.List;
public class Ui {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";


    public static void WelcomeMessage() {
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \n"
                + "What can I do for you?");
    }
    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void indentTaskDisplay(Task t) {
        System.out.println("  " + t);
    }

    public static void displayOrderedList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.get(i);
            System.out.println(i + 1 + ". " + currentTask);
        }
    }

    public static void displayTasksLeft(int tasksLeft) {
        System.out.println("Now you have " + tasksLeft + " tasks in the list");
    }

    public static void displayException(Exception e) {
        System.out.println(e);
    }

}
