package pony;

import pony.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A UI class handling all user interaction.
 */
public class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    };

    public static String printWelcome() {
        String message = "Hello! I'm Pony!!!!\nNever forget - Friendship is Magic!!!\n"
                + "My dear friend, what can I do for you?";
        return message;
    }

    public String printExit() {
        String message = "Bye, friend. Hope to see you again soon!\nNever forget - Friendship is Magic!!!";
        return message;
    }

    public String printMarkedTask(Task task) {
        String message = "Well done, my friend! I've marked this task as done:\n";
        message += task.toString();
        return message;
    }

    public String printUnmarkedTask(Task task) {
        String message = "My friend, I've marked this task as not done yet:\n";
        message += task.toString();
        return message;
    }

    public String printDeletedTask(Task task, TaskList tasks) {
        String message = "Alright my friend, I've removed this task:\n";
        message += task.toString() + "\n" + "Now you have " + tasks.getTasksCount() + " tasks in the list.";
        return message;
    }

    public String printAddedTask(Task task, TaskList tasks) {
        String message = "Got it my friend. I've added this task:\n";
        message += task.toString() + "\n" + "Now you have " + tasks.getTasksCount() + " tasks in the list.";
        return message;
    }

    public String printTaskList(TaskList tasks) {
        String message = "";
        if (tasks.sizeOf() == 0) {
            message = "There is nothing on the list!";
        } else {
            message = "Let's look the tasks in your list:\n";
            for (int i = 0; i < tasks.sizeOf(); i++) {
                int serialNumber = i + 1;
                message += serialNumber + ". " + tasks.getTask(i).toString() + "\n";
            }
        }
        return message;
    }
    public String printFindResult(ArrayList<Task> tasks) {
        String message = "";
        if (tasks.size() == 0) {
            message = "There is no matching task!";
        } else {
            message = "Alright, Here's what pony found!\n";
            for (int i = 0; i < tasks.size(); i++) {
                int serialNumber = i + 1;
                message += serialNumber + ". " + tasks.get(i).toString() + "\n";
            }
        }
        return message;
    }
}
