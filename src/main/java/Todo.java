public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[" + TaskType.TD +"]" + "[" + this.getStatusIcon() + "] " + this.getName();
    }
}
