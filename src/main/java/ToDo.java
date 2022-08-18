public class ToDo extends Task {
    public ToDo(String title, boolean done) {
        super(title, done);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
