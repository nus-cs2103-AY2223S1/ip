package duke;

public class ToDo extends Task{
    private String type;
    ToDo(String name, boolean isDone) {
        super(name, isDone);
        this.type = "[T]";
    }
    @Override
    public String toString() {
        return this.type + super.getStatus();
    }
}

