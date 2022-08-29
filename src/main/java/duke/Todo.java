package duke;
public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    String saveStringToFile() {
        return super.saveStringToFile();
    }
}
