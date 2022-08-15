public class Deadline extends Task {
    private String duedate;
    public Deadline(String description, String duedate) {
        super(description);
        this.duedate = duedate;
    }
    @Override
    public String getType() {
        return "D";
    }
    public String getDuedate() {
        return this.duedate;
    }
}
