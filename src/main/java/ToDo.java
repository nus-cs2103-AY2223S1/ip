public class ToDo extends Task {
    public ToDo(String desc) throws EmptyDescException {
        super(desc);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
