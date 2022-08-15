import java.util.ArrayList;
import java.util.List;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String str) {
        this.description = str;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + this.toString());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + this.toString());
    }

    @Override
    public String toString() {
       return "[" + getStatusIcon() + "] " + this.description;
    }

}
