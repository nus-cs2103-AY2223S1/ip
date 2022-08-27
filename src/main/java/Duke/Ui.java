package duke;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui () {
        sc = new Scanner(System.in);
    }

    public String getUserCommand() {
        return sc.nextLine();
    }

    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    public void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void fileNotFound() {
        System.out.println("File not Found!");
    }

    public void list(TaskList tasklist) {
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < tasklist.size(); x++) {
            System.out.println(String.format("%s.%s", x+1, tasklist.get(x).toString()));
        }
    }

    public void addResponse(Task addedTask, TaskList tasklist) {
        System.out.println("Got it. I've added this task:");
        System.out.println(addedTask);
        System.out.println(String.format("Now you have %s task(s) in the list", tasklist.size()));
    }

    public void deleteResponse(TaskList tasklist, int index) {
        System.out.println("Noted, I've removed this task:");
        System.out.println(tasklist.get(index).toString());
        System.out.println(String.format("Now you have %s task(s) in the list", tasklist.size() - 1));
    }

    public void markResponse(TaskList tasklist, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("%s.%s", index+1, tasklist.get(index).toString()));
    }

    public void unmarkResponse(TaskList tasklist, int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("%s.%s", index+1, tasklist.get(index).toString()));
    }

    /**
     * Finds tasks in list with matching description.
     *
     * @param tasklist tasklist from duke application.
     * @param word word to be matched.
     */
    public void find(TaskList tasklist, String word) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasklist.size(); i++) {
            if (tasklist.get(i).toString().contains(word)) {
                System.out.println(String.format("%s.%s", i + 1, tasklist.get(i).toString()));
            }
        }
    }
}
