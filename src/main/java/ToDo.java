public class ToDo extends Task {
    private static final String TYPE_SYMBOL = "[T]";

    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString();
    }
}
