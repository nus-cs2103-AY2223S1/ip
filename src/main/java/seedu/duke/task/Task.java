package seedu.duke.task;

public class Task {
    String name;
    boolean isDone;
    String TYPE = null;

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

    public boolean getDone() {
        return this.isDone;
    }

    public String getName() {
        return name;
    }
    public String getType() { return TYPE;};

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

    public void changeDesc(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return this.getStatus() + ' ' + this.getName();
    }


}
