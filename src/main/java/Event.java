public class Event extends Task {
    private final char tag = 'E';
    public static final String DELIMITER = " /at ";
    private String time;

    public Event(String description) {
        super(description.split(Event.DELIMITER)[0].substring(6));
        this.time = description.split(Event.DELIMITER)[1];
    }

    @Override
    public String printTask() {
        return String.format("[%s]%s (at: %s)",
                this.tag,
                super.printTask(),
                this.time);
    }
}
