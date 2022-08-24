package duke;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public void setDone() {
        if (this.isDone) {
            System.out.println("duke.Task is already done");
            return;
        }
        this.isDone = true;
        System.out.println("duke.Task is marked as Done \n" + this.toString());
    }

    public void setNotDone() {
        if (!this.isDone) {
            System.out.println("duke.Task is still undone");
            return;
        }
        this.isDone = false;
        System.out.println("duke.Task is marked as undone \n" + this.toString());
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public String toStorageFormat() {
        if (this.isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }
}
