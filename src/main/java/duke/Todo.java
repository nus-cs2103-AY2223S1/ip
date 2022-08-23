package duke;

public class Todo extends Task{
    private final String tag = "[T]";
    public Todo(String name) {
        super(name);
    }


    @Override
    public String toString() {
        return tag + "[" + this.getStatusIcon()  + "] " + this.getTaskName();
    }
}
