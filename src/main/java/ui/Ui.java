package ui;

import tasks.Task;
import tasks.TaskList;

public class Ui {

    public void showWelcome() {
        System.out.println(
                "Hello! I'm Duke\n" +
                        "What can I do for you?"
        );
    }

    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showCommandUnknown() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public void showAddTaskResponse(Task newTask, TaskList tasks) {
        String response = String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                newTask, tasks.getSize());
        System.out.println(response);
    }

    public void showDeleteTaskResponse(Task deletedTask, TaskList tasks) {
        String response = String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list",
                deletedTask, tasks.getSize()
        );
        System.out.println(response);
    }

    public void showMarkTaskResponse(Task markedTask) {
        String response = String.format(
                "Nice! I've marked this task as done:\n  %s",
                markedTask);
        System.out.println(response);
    }

    public void showUnmarkTaskResponse(Task unmarkedTask) {
        String response = String.format(
                "OK, I've marked this task as not done yet:\n  %s`",
                unmarkedTask);
        System.out.println(response);
    }

    public void showTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        System.out.print(tasks.toString());
    }

}
