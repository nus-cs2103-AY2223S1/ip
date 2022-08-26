package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        System.out.println("Hello! I am a ToDos, Events, Deadlines and Talk Bot, otherwise known as TEDTalk\n" +
                "What can I do for you today?");
    }

    public void showBye() {
        System.out.println("Goodbye! I hope to see you again soon.");
    }

    public void showMark(Task task) {
        System.out.println("Task successfully completed!\n" + task);
    }

    public void showUnmark(Task task) {
        System.out.println("Task incomplete.\n" + task);
    }

    public void showAdd(Task task, int size) {
        System.out.println("Successfully added new task:\n" + task +
                "\nYou have " + size + " task(s) in the list.");
    }

    public void showList(TaskList taskList) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
    }

    public void showDelete(Task task, int size) {
        System.out.println("Understood. I have removed this task:\n" + task +
                "\nYou have " + size + " task(s) in the list.");
    }

    public void showDate(ArrayList<Task> tasks) {
        if (tasks.size() != 0) {
            System.out.println("Here are the task(s) on that date:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else {
            System.out.println("You have no tasks on that date.");
        }
    }

    public void showLoadingError() {
        System.out.println("No saved data found.");
    }

    public void showError(String msg) {
        System.out.println(msg);
    }

    public void showFind(ArrayList<Task> tasks) {
        if (tasks.size() != 0) {
            System.out.println("Here are the matching task(s):");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else {
            System.out.println("There are no tasks matching that word.");
        }
    }
}
