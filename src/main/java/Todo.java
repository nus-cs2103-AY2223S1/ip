public class Todo extends Task {
    private static final String type = "[T]";

    public Todo(String name) throws MissingDescriptionException {
        super(name);
    }

    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return type + comp + name;
    }

    @Override
    public String toData() {
        String type = "T";
        String completed = this.completed ? "1" : "0";
        return type + "//" + completed +"//" + name;
    }
}
