package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Dude:\n" + "Hey Dude here\n" + "What can I do for you?");
        this.showLine();
    }

    public void showDude() {
        System.out.println("Dude: ");
    }
    public void showLine() {
        System.out.println("________________");
    }

    public void showLoadingError() {
        System.out.println("LOADING ERROR ... I died..XOX");
    }

    public void showError(String message)
    {
        System.out.println("ERROR... I just died XP\n" + message);
    }

    public void sayBye() {
        System.out.println("Bye^2!! :p");
    }

    public void sayList(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            System.out.println("You have nothing due soon! :)");
            return;
        }
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task: arr) {
            System.out.println(i + "." + arr.get(i - 1).toString());
            i++;
        }
    }

    public void sayMarked(int num, ArrayList<Task> arr) {
        System.out.println("Nice! I've marked this task as done:\n"
                + arr.get(num - 1).toString());
    }

    public void sayUnmarked(int num, Task t) {
        System.out.println("OK, I've marked this task as not done yet:\n"
                + t.toString());
    }

    public void sayAdded(ArrayList<Task> arr) {
        System.out.println("Got it. I've added this task:\n"
                +arr.get(arr.size() - 1).toString()
                + "\nNow you have " + arr.size() + " tasks in the list.");
    }

    public void sayDeleted(Task deletedTask, int size) {
        System.out.println("Noted. I've removed this task:\n" + deletedTask.toString()
                + "\nNow you have " + size + " tasks in the list.");
    }

    public void sayMatching(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            System.out.println("There are no matches unfortunately :(");
            return;
        }
        System.out.println("Here are the matching tasks in your list:");
        int i = 1;
        for (Task task: arr) {
            System.out.println(i + "." + arr.get(i - 1).toString());
            i++;
        }
    }

    public String readCommand() {
        System.out.println("Me: \t");
        return sc.nextLine();
    }
}