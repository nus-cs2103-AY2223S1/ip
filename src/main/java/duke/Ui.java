package duke;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that deals with interactions with the user
 */
public class Ui {

    public static String getListMessage(TaskList tl) {
        String message = "";
        for (int i = 1; i < tl.count() + 1; i++) {
            message += (i) + ". " + tl.getTask(i - 1) + "\n";
        }
        return message;
    }

    public static String getMarkMessage(Task t) {
        return "Nice! I've marked this task as done:\n  [X] " + t.description;
    }

    public static String getUnmarkMessage(Task t) {
        return "OK, I've marked this task as not done yet:\n  [ ] " + t.description;
    }

    public static String todoMessage(Todo t, int count) {
        return "Got it. I've added this task:\n " + t +
                "\nNow you have " + count + " tasks in the list.";
    }

    public static String deadlineMessage(Deadline d, int count) {
        return "Got it. I've added this task:\n " + d +
                "\nNow you have " + count + " tasks in the list.";
    }

    public static String eventMessage(Event e, int count) {
        return "Got it. I've added this task:\n " + e +
                "\nNow you have " + count + " tasks in the list.";
    }

    public static String deleteMessage(Task t, int count) {
        return "Noted. I've removed this task: \n " + t + "\nNow you have "
                + count + " tasks in the list.";
    }

    public static String findMessage(ArrayList<Task> lst) {
        int counter = 1;
        String tasks = "Here are the matching tasks in your list:\n";
        for (Task t: lst) {
            tasks += counter + ". " + t.toString() + "\n";
            counter++;
        }
        return tasks;
    }

    /**
     * Method that prints a loading error message
     */
    public static void showLoadingError() {
        System.out.println("Loading error...");
    }
}
