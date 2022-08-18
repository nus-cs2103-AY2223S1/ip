public class Todo extends Task{


    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + (super.isCompleted() ? "[X] " : "[ ] ") + super.getTaskName();
    }
}
