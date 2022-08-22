package duke;

import java.io.Serializable;
public abstract class Task implements Serializable {

    private String item;
    private boolean isCompleted;

    public Task(String item) {
        this.item = item;
        this.isCompleted = false;
    }

    // this is implemented as a toggle; could be problematic in future
    public void completeToggle() {
        this.isCompleted = !this.isCompleted;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    @Override
    public String toString() {
        if (isCompleted) {
            return "[X] " + item;
        }
        return "[ ] " + item;
    }

}
