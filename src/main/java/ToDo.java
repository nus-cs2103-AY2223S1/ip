public class ToDo extends Task {
    public ToDo(String name, boolean initialComplete) {
        super(name, initialComplete);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
