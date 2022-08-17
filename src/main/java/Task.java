package main.java;

public class Task {
    protected String name;
    protected boolean isDone;

    // constructor
    public Task(String taskName) {
        name = taskName;
        isDone = false;
    }

    // getStatusIcon taken from cs2103 module website
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }


    public static void markAsDone(Task task) {
        task.isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    public static void markAsUndone(Task task) {
        task.isDone = false;
        System.out.println("Ok. I've marked this task as not done yet: ");
        System.out.println(task.toString());
    }



}
