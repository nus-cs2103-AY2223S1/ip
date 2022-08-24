package duke.task;

public class ToDo extends Task {
    private String name;
    public ToDo(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

    public String parse() {
        if (this.getHasCompleted()) {
            return "T#1#" + this.name;
        } else {
            return "T#0#" + this.name;
        }

    }
}
