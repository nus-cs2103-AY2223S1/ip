public class ToDos extends Task {
    public ToDos(String task, boolean done) {
        super(task, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "todo " + super.toSaveString();
    }
}
