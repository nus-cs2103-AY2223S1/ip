public class Deadline extends Task {
    private static final String ICON = "D";
    protected String dateString;

    Deadline(String description, String dateString) {
        super(description);
        this.dateString = dateString;
    }

    @Override
    public String toDataString(String separator) {
        return String.format("%s%s%s%s", ICON, super.toDataString(separator), separator, dateString);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", ICON, super.toString(), dateString);
    }
}
