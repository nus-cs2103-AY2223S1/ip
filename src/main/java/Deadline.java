public class Deadline extends Task {
    /**
     * Child class of Task with a deadline
     */
    private static final String SYMBOL = "[D]";
    protected String date;
    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return SYMBOL + super.toString() + "(by: " + date + ")";
    }
}
