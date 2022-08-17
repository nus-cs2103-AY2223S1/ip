public class ToDo extends Task{
    ToDo(String description) {
        super(description);
    }

    public String getToDoStatusIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getToDoStatusIcon() + "]" + super.toString();
    }
}
