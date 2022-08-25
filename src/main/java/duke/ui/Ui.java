package duke.ui;

import duke.model.Task;
import duke.model.TaskList;

import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui(Scanner sc) {
        this.sc = sc;
    }

    public void greetUser() {
        this.showDivider();
        System.out.println("\tHey there! I'm Duke!");
        System.out.println("\tHow may I help you? :)");
    }

    public static void sayBye() {
        System.out.println("\tNice seeing you! Bye!");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void close() {
        this.sc.close();
    }

    public static void add(Task task) {
        System.out.println("\tGot it! I've added this task!");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + Task.getNumOfTasks() + " tasks in the list!");
    }

    public static void list(TaskList taskList) {
        System.out.println(taskList);
    }

    public static void delete(Task task) {
        System.out.println("\tAlright! The following task has been deleted!");
        System.out.println("\t\t" + task);
    }

    public void mark(Task task) {
        System.out.println("\tAlright! Marked this task as done!");
        System.out.println("\t\t" + task);

    }

    public void unmark(Task task) {
        System.out.println("\tOkay! Unmarked this task!");
        System.out.println("\t\t" + task);
    }

    public void find(String description, TaskList taskList) {
        System.out.println("\tHere are the matching tasks in your list!");
        int num = 1;
        for (int i = 1; i < Task.getNumOfTasks() + 1; i++) {
            Task t = taskList.getTask(i);
            if (t.contains(description)) {
                System.out.println("\t\t" + num + ". " + t);
                num += 1;
            }
        }
    }

    public void showDivider() {
        System.out.println("\t-----------------------------------------------------");
    }
}
