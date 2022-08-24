package duke;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    private Scanner myScanner = new Scanner(System.in);
    public void showWelcome() {
        String logo = "____________________________________________________________\n" + "Hello from\n" +
                " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n" +
                "How may I assist you?\n" +
                "____________________________________________________________\n";
        System.out.println(logo);
    }

    public void showLoadingError() {
        System.out.println("Unable to load file");
    }

    public String readCommand() {
        System.out.println("Please enter your command:");
        String command = myScanner.nextLine();
        return command;
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void showBye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    public void showMarked(Task task) {
        String taskCompletion = "Nice! I've marked this task as done:\n" + "  " + task.toString() + "\n";
        System.out.println(taskCompletion);
    }

    public void showUnMarked(Task task) {
        String taskUnCompletion = "Ok, I've marked this task as not done yet:\n" + "  " + task.toString() + "\n";
        System.out.println(taskUnCompletion);
    }

    public void showList(ArrayList<Task> tasks) {
        String newList = "Here are the tasks in your list:\n";
        int count = 1;
        for (Task item: tasks) {
            newList += (count + "." + item.toString() + "\n");
            count++;
        }
        System.out.println(newList);
    }

    public void showDelete(Task task, int total) {
        String message = "Noted. I've removed this task:\n" + " " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        System.out.println(message);
    }

    public void showAdd(Task task, int total) {
        String printLine = "Got it. I've added this task:\n" + "  " + task.toString() + "\n" + "Now you have " + total
                + " tasks in the list.\n";
        System.out.println(printLine);
    }
}
