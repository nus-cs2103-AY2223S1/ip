public class ToDo extends Task{
    public ToDo(String detail) {
        super(detail);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
