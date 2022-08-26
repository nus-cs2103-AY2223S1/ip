package task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String stringify() {
        return "T##" + super.stringify();
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
