public class ToDo extends Task {

    public ToDo(String item) {
        super(item);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
