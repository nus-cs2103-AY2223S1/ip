public class ToDo extends Task {
    private static final String TYPE = "[T]";

    public ToDo(String name) {
        super(name);
    }

    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    @Override
    public String toString() {
        return TYPE + super.toString();
    }
}
