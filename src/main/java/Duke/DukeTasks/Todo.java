package Duke.DukeTasks;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String fileForm() {
        return "T" + "," + super.fileForm();
    }
}