public class Todo extends Task {
    private static final String type = "[T]";

    public Todo(String name, int count) throws MissingDescriptionException {
        super(name, count);
    }

    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return String.format("%d." + type + comp + name, count);
    }

    @Override
    public String toStr() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return type + comp + name;
    }
}
