package unc.task;

public class Todo extends Task {


    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String done) {
        super(description, done == "true");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStorageString() {
        return "T" + "///" + this.description + "///" + " " + "///" + this.isDone;
    }
}