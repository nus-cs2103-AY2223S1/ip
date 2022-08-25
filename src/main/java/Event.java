public class Event extends Task {
    private static final String ICON = "E";
    protected String dateString;

    Event(String description, String dateString) {
        super(description);
        this.dateString = dateString;
    }

    @Override
    public String toDataString(String separator) {
        return String.format("%s%s%s%s", ICON, super.toDataString(separator), separator, dateString);
    }

    @Override
    public String toString() {
        return String.format("[%S]%s (at: %s)", ICON, super.toString(), dateString);
    }
}
