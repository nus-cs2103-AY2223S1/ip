package duke;

import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {
    private String logo;
    private Scanner scanner;

    public Ui() {
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.scanner = new Scanner(System.in);
    }

    public final String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello from\n" + logo + "I'm Duke! \n What can I do for you?");
    }

    public void printError(DukeException error) {
        printWithLineBreak(error.getMessage());
    }

    public void printWithLineBreak(String text) {
        System.out.println("\n" + text + "\n");
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void printArray(TaskList taskList) {
        ArrayList<Task> list = taskList.getList();
        if (list.size() == 0) {
            printWithLineBreak("You have no tasks in your list.");
        } else {
            String result = "Here are the tasks in your list:\n";
            for (int i = 0; i < list.size(); i++) {
                result += "\t" + (i + 1) + ". " + list.get(i) + "\n";
            }
            printWithLineBreak(result);
        }
    }

    public void printExitMessage() {
        printWithLineBreak("Goodbye! Hope to see you again!");
    }

    public void printAddMessage(Task task, TaskList taskList) {
        printWithLineBreak(
                "Got it. I've added this task:\n" + task + "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    public void printDeleteMessage(Task task) {
        printWithLineBreak("Noted. I've removed this task:\n" + task);
    }

    public void printMarkMessage(Task task) {
        printWithLineBreak("Nice! I've marked this task as done:\n" + task);
    }

    public void printUnmarkMessage(Task task) {
        printWithLineBreak("Okay, I've unmarked this task as undone:\n" + task);
    }
}
