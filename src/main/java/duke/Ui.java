package duke;

import duke.task.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    Scanner scn;

    public void greet() {
        String logo = "\n" +
                "   ██▓    ▄▄▄       ███▄ ▄███▓▓█████▄  ▄▄▄\n" +
                "  ▓██▒   ▒████▄    ▓██▒▀█▀ ██▒▒██▀ ██▌▒████▄\n" +
                "  ▒██░   ▒██  ▀█▄  ▓██    ▓██░░██   █▌▒██  ▀█▄\n" +
                "  ▒██░   ░██▄▄▄▄██ ▒██    ▒██ ░▓█▄   ▌░██▄▄▄▄██\n" +
                "  ░██████▒▓█   ▓██▒▒██▒   ░██▒░▒████▓  ▓█   ▓██▒\n" +
                "  ░ ▒░▓  ░▒▒   ▓▒█░░ ▒░   ░  ░ ▒▒▓  ▒  ▒▒   ▓▒█░\n" +
                "  ░ ░ ▒  ░ ▒   ▒▒ ░░  ░      ░ ░ ▒  ▒   ▒   ▒▒ ░\n" +
                "    ░ ░    ░   ▒   ░      ░    ░ ░  ░   ░   ▒\n" +
                "      ░  ░     ░  ░       ░      ░          ░  ░\n" +
                "                               ░\n";
        System.out.print(logo);
        System.out.println("Hi, I am LaMDA.\nHow may I assist you today?\n");
        scn = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("\t____________________________________________");
    }

    public String readCommand() {
        return scn.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error locating \"tasks.dat\".\nA new file will be created.");
    }

    public void showError(String s) {
        System.out.println(s);
    }

    public void exitTask() {
        System.out.println("\t It's a great time talking with you.\n\t See you next time!");
        scn.close();
    }

    public void listTask(TaskList tasks) {
        System.out.println("\t Here are the tasks in your list:");
        tasks.listTasks();
    }

    public void todoTask(TaskList tasks, Todo todo) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + todo.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void deadlineTask(TaskList tasks, Deadline deadline) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + deadline.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void eventTask(TaskList tasks, Event event) {
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + event.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void markTask(Task task) {
        System.out.println("\t I've marked this task as done:");
        System.out.println("\t   " + task.toString());
    }

    public void unmarkTask(Task task) {
        System.out.println("\t I've marked this task as not done yet:");
        System.out.println("\t   " + task.toString());
    }

    public void deleteTask(TaskList tasks, Task task) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + task.toString());
        System.out.println("\t Now you have " + tasks.size() + " tasks in the list.");
    }

    public void findTask(TaskList tasks, String description) {
        System.out.println("\t Here are the matching tasks in your list:");
        ArrayList<Task> temp = tasks.find(description);
        for (int i = 0; i < temp.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + temp.get(i).toString());
        }
    }
}
