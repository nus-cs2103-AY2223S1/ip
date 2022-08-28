package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    public String formatTask() {
        String var10000 = super.getStatusIcon();
        return "[T] [" + var10000 + "] " + super.description;
    }

    public String toString() {
        String var10000 = super.getStatusIcon();
        return "T/" + var10000 + "/" + super.description;
    }
}
