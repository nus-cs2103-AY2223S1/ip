public class Deadline extends Task {
    private String date;

    Deadline(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    public String SaveString() {
        return String.format("D | %s | %s | %s\n", super.isDone ? "1" : "0",
                super.description, date);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }
}
