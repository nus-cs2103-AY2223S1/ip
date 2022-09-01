package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;
public class Ui {
    private Scanner sc = new Scanner(System.in);
    protected String reply;

    public String showWelcome() {
        return "Dude: \n" + "Hey Dude here\n" + "What can I do for you?";
    }

//    public void showLine() {
//        System.out.println("________________");
//    }

    public String showLoadingError() {
        return "LOADING ERROR ... I died..XOX";
    }

    public String showError(String message) {
        return "ERROR... I just died XP\n" + message;
    }

    public void sayBye() {
        reply = "Bye^2!! :p";
    }

    public void sayList(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            reply = "You have nothing due soon! :)";
            return;
        }
        int i = 1;
        String s = "Here are the tasks in your list: \n";
        for (Task task: arr) {
            s += (i + "." + arr.get(i - 1).toString() + "\n");
            i++;
        }
        reply = s;
    }

    public void sayMarked(int num, ArrayList<Task> arr) {
        reply = "Nice! I've marked this task as done:\n"
                + arr.get(num - 1).toString();
    }

    public void sayUnmarked(int num, Task t) {
        reply = "OK, I've marked this task as not done yet:\n"
                + t.toString();
    }

    public void sayAdded(ArrayList<Task> arr) {
        reply = "Got it. I've added this task:\n"
                + arr.get(arr.size() - 1).toString()
                + "\nNow you have " + arr.size() + " tasks in the list.";
    }

    public void sayDeleted(Task deletedTask, int size) {
        reply = "Noted. I've removed this task:\n" + deletedTask.toString()
                + "\nNow you have " + size + " tasks in the list.";
    }

    public void sayMatching(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            reply = "There are no matches unfortunately :( \n";
            return;
        }
        String s = "Here are the matching tasks in your list:";
        int i = 1;
        for (Task task: arr) {
            s += (i + "." + arr.get(i - 1).toString());
            i++;
        }
        reply = s;
    }

    public String readCommand() {
        String s = "Me: \t";
        return s + sc.nextLine();
    }
}