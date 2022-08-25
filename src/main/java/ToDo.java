public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String formatTask() {
        return "[T] [" + super.getStatusIcon() + "] " + super.description;
    }

    @Override
    public String toString() {
        return "T/" + super.getStatusIcon() + "/" + super.description;
    }
}