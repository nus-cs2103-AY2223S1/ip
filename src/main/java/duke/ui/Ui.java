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

    public void sayBye() {
        System.out.println("\tNice seeing you! Bye!");
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void close() {
        this.sc.close();
    }

    public void add(Task task) {
        System.out.println("\tGot it! I've added this task!");
        System.out.println("\t\t" + task);
        System.out.println("\tYou now have " + Task.getNumOfTasks() + " tasks in the list!");
    }

    public void list(TaskList taskList) {
        System.out.println(taskList);
    }

    public void delete(Task task) {
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

    public void showDivider() {
        System.out.println("\t-----------------------------------------------------");
    }
}
