public class Todo extends Task {
    public Todo(String task) {
        super(task, "todo");
    }

    @Override
    public String toFileData() {
        return "T | " + super.toFileData();
    }

    @Override
    public String toString() { 
        return String.format("[T]%s", super.toString());
    }
}
