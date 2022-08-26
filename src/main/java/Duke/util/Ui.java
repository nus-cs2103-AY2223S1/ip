package Duke.util;

import Duke.task.Task;
import Duke.task.TaskList;

public class Ui {
    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke.Duke\n" + "What can I do for you?");
    }

    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void addTask(Task task, TaskList tasks) {
        System.out.println( "Noted. I've removed this Duke.Duke.task: \n" + task.toString() +
                "\nNow you have " + tasks.getLength()
                + " tasks in the list");
    }
    public void respond(String response) {
       System.out.println(response);
    }
}
