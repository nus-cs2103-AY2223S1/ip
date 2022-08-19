public class Deadline extends Task {
    protected String dateString;

    Deadline(String description, String dateString) {
        super(description);
        this.dateString = dateString;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dateString);
    }
}
