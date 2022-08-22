public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean done) {
        super(description);
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return "T | " + (isDone ? "1" : "0") + " | " + this.description;
    }
}