import java.util.ArrayList;
import java.util.List;

public class Task {

    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Override the toString() method in the Object class. "[X]" is added in front of the task name if it is marked
     * as done, and "[ ]" is added in front otherwise.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
