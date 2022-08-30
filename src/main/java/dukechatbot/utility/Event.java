package dukechatbot.utility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class implements a subclass of Task that encapsulates the type of tasks that has a
 * duration associated with it.
 *
 * @author A0233290M
 * @version Week3
 */
public class Event extends Task {
    /**
     * Defines the Tag associated with this type of Task.
     */
    protected static final String TAG = "[E]";
    /**
     * Defines the Date time format to read in the commands passed by client for processing.
     */
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /**
     * Defines the field that encapsulates the end of the duration of the Event instantiated.
     */
    protected LocalDateTime end;

    /**
     * Constructs an instance of the Event class with its associated attributes.
     *
     * @param descriptor describes the Event task that is to be instantiated.
     * @param duration describes the duration associated with the instance of the Event.
     */
    public Event(String descriptor, String duration) {
        super(descriptor);
        this.time = LocalDateTime.parse(duration.substring(0,16), DTF);
        this.end = LocalDateTime.parse(duration.substring(0,10) + " " + duration.substring(17), DTF);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return TAG + super.toString() + " (at: "
                + this.time.format(DTF) + " to "
                + this.end.format(DTF) + ")";
    }
}
