package bob;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSave() { return "T | " + super.toSave(); }

    @Override
    public String toString() { return "[T]" + super.toString(); }
}
