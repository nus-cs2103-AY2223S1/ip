package task;
public class Todo extends Task {

    protected String by;
    private final static String ICON = "T";

    public Todo(String description) {
        super(description, ICON);
    }

    @Override
    public String toString() {
        return String.format("[T]" + "[%s] " + super.toString(), super.getStatusIcon());
    }

    public String toSave() {
        return String.format("[T]" + "[%s] " + super.toString(), super.getStatusIcon());
    }
}