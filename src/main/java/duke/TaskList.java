package main.java.duke;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private static final String line = "-----------------------------";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> ls) {
        this.tasks = ls;
    }

    public void listAll() {
        System.out.println(line + "\n");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println((i + 1) + "." + t);
        }
        System.out.println(line + "\n");
    }

    public void mark(int idx) {
        Task t = tasks.get(idx);
        t.markIsDone();
        System.out.println(line + "\n"
                + "Nice! I've marked this task as done:\n"
                + "[X] " + t + "\n" +
                line + "\n");
    }

    public void unmark(int idx) {
        Task t = tasks.get(idx);
        t.unmarkIsDone();
        System.out.println(line + "\n"
                + "OK, I've marked this task as not done yet:\n"
                + "[ ] " + t + "\n"
                + line + "\n");
    }

    public void add(Task t) {
        tasks.add(t);
        System.out.println(line + "\n" +
                "Got it. I've added this task: " + "\n"
                + t + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.\n"
                + line + "\n");
    }

    public void delete(int idx) {
        Task t = tasks.remove(idx);
        System.out.println(line + "\n" + "Noted. I've removed this task:" + "\n"
                + t + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.\n" +
                line + "\n");
    }

}
