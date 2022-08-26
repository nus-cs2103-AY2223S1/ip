package duke;

public class ToDos extends Task{
    private String type;

    ToDos(String name, boolean isDone) {
        super(name, isDone);
        this.type = "[T]";
    }

    @Override
    public String toString() {
        return this.type + super.getStatus();
    }
}
