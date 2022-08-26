package duke.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String writeData() {
        int mark = isDone ? 1 : 0;
        return "T#" + mark + "#" + this.name;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
