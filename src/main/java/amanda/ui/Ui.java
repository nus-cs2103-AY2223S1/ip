package amanda.ui;

import amanda.task.*;
import amanda.manager.*;
import java.util.Scanner;

public class Ui {
    public Ui() {

    }

    public void showWelcome() {
        String logo ="                                           _\n"
                +"            __ _ _ __ ___   __ _ _ __   __| | __ _\n"
                +"           / _' | '_ ` _ \\ / _` | '_ \\ / _` |/ _` |\n"
                +"          | (_| | | | | | | (_| | | | | (_| | (_| |\n"
                +"           \\__,_|_| |_| |_|\\__,_|_| |_|\\__,_|\\__,_|\n";
        System.out.println(logo);
        System.out.println("    ............................................................");
        System.out.println("     Urgh! It's you\n     What do you want?");
        System.out.println("    ............................................................\n");
    }

    public void showLine() {
        System.out.println("    ............................................................");
    }

    public void showError(String string) {
        System.out.println(string);
    }

    public void showAddCommand(TaskManager tasks, Task task) {
        System.out.println("     Let's see if you will actually get this done:");
        System.out.println("        " + task);
        System.out.println("     Now you have " + tasks.getList().size() + " tasks in the list.");
    }

    public void showListCommand() {
        System.out.println("     Here are the tasks in your list, now stop disturbing me:");
    }

    public void showMarkCommand(TaskManager tasks, int taskNo) {
        System.out.println("     Wow! You actually finished the task, I didn't think you have it in you.");
        System.out.println("        " + tasks.getList().get(taskNo - 1));
    }

    public void showUnmarkCommand(TaskManager tasks, int taskNo) {
        System.out.println("     I knew it! you didn't actually finish it.");
        System.out.println("        " + tasks.getList().get(taskNo - 1));
    }

    public void showDeleteCommand(TaskManager tasks) {
        System.out.println("     It's fine! Out of sight, out of mind. Am i right?");
        System.out.println("     Now you have " + tasks.getList().size() + " tasks in the list.");
    }

    public void showExitCommand() {
        System.out.println("     Finally! I'll take a nap, please don't call me again.");
    }

    public String readCommand(Scanner sc) {
        return sc.nextLine();
    }

}
