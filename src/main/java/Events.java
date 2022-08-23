public class Events extends Task{
    protected String period;

    public Events(String description, String period) {
        super(description);
        this.period = period;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }
}
