public class Todo extends Task{


    public Todo(String name) {
        super(name);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getTime() {
        return null;
    }

    @Override
    public String toString() {
        return "[T] " + (super.isCompleted() ? "[X] " : "[ ] ") + super.getTaskName();
    }
}
