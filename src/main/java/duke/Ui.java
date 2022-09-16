package duke;

import java.util.Scanner;

public class Ui {
    public Scanner sc = new Scanner(System.in);

    public void print(String string) {
        System.out.println(string);
    }

    public void greet() {
        print("Hello! I'm duke.Duke");
        print("What can I do for you?");
    }

    public String read() {
        return sc.nextLine();
    }

    public void addTask(Task task) {
        print("Got it. I've added this task:");
        print(task.toString());
    }

    public void printTasks(TaskList tasks) {
        print("Here are the tasks in your list:");
        for (int a = 0; a < tasks.size(); a++) {
            print((a + 1) + ". " + tasks.get(a));
        }
    }

    public void markAsDone(Task task) {
        print("Nice! I've marked this task as done:");
        print(task.toString());
    }

    public void markNotDone(Task task) {
        print("OK, I've marked this task as not done yet:");
        print(task.toString());
    }

    public void infoCount(int count) {
        print("Now you have " + count + " tasks in the list.");
    }

    public void deleteTask(Task task) {
        print("Noted. I've removed this task:");
        print(task.toString());
    }

    /**
     * Print all matching tasks to the UI.
     */
    public void findTasks(TaskList tasks) {
        print("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            print((i + 1) + ". " + tasks.get(i));
        }
    }

    public void close() {
        print("Bye. Hope to see you again soon!");
        sc.close();
    }
}
