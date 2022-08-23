public class ToDo extends Task{
    public ToDo(String detail) {
        super(detail);
    }

    public ToDo(String detail, boolean isDone) {
        super(detail, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String storedData() {
        return "T" + "|" + super.storedData();
    }
}
