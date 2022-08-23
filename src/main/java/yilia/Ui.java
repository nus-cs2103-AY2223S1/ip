package yilia;
import yilia.task.Task;
import yilia.task.TaskList;

import java.util.Scanner;

public class Ui {
    public void showWelcome() {
        String logo = "  \\‾\\     /‾/  |‾|  |‾|          |‾|       /‾‾‾\\      \n" +
                "   \\ \\   / /   | |  | |          | |      / /‾\\ \\     \n" +
                "    \\ \\_/ /    | |  | |          | |     / /___\\ \\    \n" +
                "     ‾| |‾     | |  | |          | |    / /_____\\ \\   \n" +
                "      | |      | |  | |_______   | |   / /       \\ \\  \n" +
                "      |_|      |_|  |_________|  |_|  /_/         \\_\\ \n";
        System.out.println("Hello! I'm Yilia\n" + logo + "What can I do for you?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("_______");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError() {
        System.out.println("The file cannot be loaded.");
    }

    public void showAddStatus(TaskList tasks) {
        System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size()) + "\nNow you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.");
    }

    public void showMarkStatus(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public void showUnmarkStatus(Task task) {
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    public void showDeleteStatus(Task task, TaskList tasks) {
        System.out.println("Noted. I've removed this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}