public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, int status, String date) {
        super(description, status);
        this.date = date;
    }

    @Override
    public String parseToSaveData() {
        return "E" + "|" + super.parseToSaveData() + "|" + date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
