public class Deadline extends Task {
    private String due;
    public Deadline(String description, String due) {
        super(description);
        this.due = due;
    }
    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDescription() {
        return super.getDescription() + "(by: " + this.due + ")";
    }
}
