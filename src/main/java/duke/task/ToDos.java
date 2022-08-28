package duke.task;

public class ToDos extends Task {

    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toMemoryString() {
        return "T"
                + " | "
                + (this.isDone() ? "1" : "0")
                +  " | "
                + this.getName();
    }
}
