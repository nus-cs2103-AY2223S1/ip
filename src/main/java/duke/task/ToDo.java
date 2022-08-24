package duke.task;

public class ToDo extends Task{

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    public String tag() {
        return "T";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", tag(), super.toString());
    }

    @Override
    public String savedString() {
        return String.format("%s,%s", tag(), super.savedString());
    }

}
