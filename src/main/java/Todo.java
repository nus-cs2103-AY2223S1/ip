public class Todo extends Task {

    public Todo(String desc) {
        super(desc);
    }

    public String getDescription() {
        return super.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getTaskType() {
        return "T";
    }
}
