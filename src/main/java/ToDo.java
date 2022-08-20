public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getDate() {
        return null;
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
