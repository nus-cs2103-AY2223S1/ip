package duke.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Encapsulates a Event
 */
public class Event extends Task {
    private String at;

    /**
     * Constructor for an Event
     *
     * @param name Name / Description of the event
     * @param at Description of where and when the event is at
     */
    public Event(String name, String at) {
        super(name, 'E');
        this.at = at;
    }

    @Override
    public void update(String args) {
        Pattern fullPattern = Pattern.compile("(.+)/at(.+)");
        Pattern atPattern = Pattern.compile("/at(.+)");
        if (Pattern.matches("(.+)/at(.+)", args)) {
            Matcher m = fullPattern.matcher(args);
            m.find();
            super.update(m.group(1).trim());
            this.at = m.group(2).trim();
        } else if (Pattern.matches("/at(.+)", args)) {
            Matcher m = atPattern.matcher(args);
            m.find();
            this.at = m.group(1).trim();
        } else {
            super.update(args);
        }
    }

    /**
     * Returns string representation of the event consisting of the string representation of Event, [E], the completion
     * status of the event and the event description
     * */
    @Override
    public String toString() {
        return String.format(super.toString() + " (at: %s)", at);
    }
}
