public class Deadline extends Task {
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String toStringSaveFormat() {
        return String.format("D,%s,%s,%s\n", isDone ? "1" : "0", this.description, this.dueDate);
    }

    @Override
    public String toString() {
        return String.format("[D][%s]%s(by:%s)", this.isDone ? "X" : " ", this.description, this.dueDate);
    }
}
