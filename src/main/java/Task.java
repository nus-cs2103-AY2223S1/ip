import java.util.Scanner;

public class Task extends Duke{

    private final String task;
    private boolean completed;

    public Task(String task) {
        this.task = task;
        this.completed = false;
    }

    // Marks the task as done
    public void mark() {
        this.completed = true;
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + this);
        System.out.println("    ____________________________________________________________\n");
    }

    // Marks the task as done
    public void unmark() {
        this.completed = false;
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + this);
        System.out.println("    ____________________________________________________________\n");
    }

    public String toString() {
        if (completed) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }
}
