package tako;

import tako.task.Task;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showAdd(Task task, int taskCount) {
        System.out.println("added: " + task);
        System.out.println("Total tasks: " + taskCount);
    }

    public void showDelete(Task task, int taskCount) {
        System.out.println("deleted: " + task);
        System.out.println("Total tasks: " + taskCount);
    }

    public void showError(String s) {
        System.out.println("An error has occurred.");
        System.out.println(s);
    }

    public void showLine() {
        System.out.println("_______________");
    }

    public void showList(TaskList tasks) {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            System.out.printf("%d.%s\n", i + 1, task);
        }
    }

    public void showLoadingError() {
        System.out.println("Tasks failed to load.");
        System.out.println("A new task list will be used instead.");
    }

    public void showMark(Task task) {
        System.out.println("marked: " + task);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Tako.");
        System.out.println("What do you want?");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void exit() {
        System.out.println("Bye, until next time...");
        sc.close();
    }
}
