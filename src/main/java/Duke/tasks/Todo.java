package Duke.tasks;

public class Todo extends Task {

    public Todo(String task) {
        super(task);
    }

    @Override
    public char getTaskType() {
        return 'T';
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
