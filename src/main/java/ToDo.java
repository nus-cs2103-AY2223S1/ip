public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String stringifyTask() {
        return String.format("%s | %s", "T", super.stringifyTask());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
