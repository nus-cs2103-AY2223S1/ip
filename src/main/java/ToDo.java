public class ToDo extends Task{
    ToDo(String description) {
        super(description);
    }

    public String getTaskTypeIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + super.toString();
    }
}
