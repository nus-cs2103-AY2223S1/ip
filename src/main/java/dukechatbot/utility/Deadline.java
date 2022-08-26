package dukechatbot.utility;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class implements a subclass of Task that encapsulates the type of tasks that has a specific
 * deadline.
 *
 * @author A0233290M
 * @version Week3
 */
public class Deadline extends Task {
    /**
     * Defines the tag associated with this type of Task.
     */
    protected static final String TAG = "[D]";
    /**
     * Defines the Date time format to read in the commands passed by client for processing.
     */
    protected static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs an instance of the Deadline class with its associated attributes.
     *
     * @param descriptor describes the Deadline task that is to be instantiated.
     * @param due describes the deadline to be associated with the deadline task to be instantiated.
     */
    public Deadline(String descriptor, String due) {
        super(descriptor);
        this.time = LocalDateTime.parse(due, DTF);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return TAG + super.toString() + " (by: " +
                this.time.format(DTF) + ")";
    }
}
