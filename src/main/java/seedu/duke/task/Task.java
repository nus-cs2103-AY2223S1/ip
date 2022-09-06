package seedu.duke.task;

public class Task {
    String name;
    boolean isDone;
    String type = "[T]";

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }


    public String getStatus() {
        if (isDone) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean markDone() {
        if (isDone) {
            return false;
        }
        this.isDone = true;
        return true;
    }

    public boolean markUndone() {
        if (!isDone) {
            return false;
        }
        this.isDone = false;
         return true;
    }

    @Override
    public String toString() {
        return this.getStatus() + ' ' + this.getName();
    }


}
