public class Deadline extends Task {
    String date;

    Deadline(String task_description, String date) {
        super(task_description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
