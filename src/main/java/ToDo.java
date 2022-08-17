public class ToDo extends Task {

    public ToDo(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
