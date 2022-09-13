package duke.modules.todos;

import duke.MessagefulException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Events - tasks with a time range.
 */
public class Event extends Task {
    private String timeRange;

    /**
     * Constructor
     *
     * @param name The name of the task.
     * @param timeRange The time range of the task.
     */
    public Event(String name, String timeRange) {
        this(name, false, timeRange);
    }

    /**
     * Constructor
     *
     * @param name      The name of the task
     * @param done      Whether the task is done.
     * @param timeRange The time range of the task.
     */
    public Event(String name, boolean done, String timeRange) {
        super(name, done);
        this.timeRange = timeRange;
    }

    //@@author parnikkapore-reused
    // Adapted from https://stackoverflow.com/questions/54593569/#54593895
    private final static Pattern CHAT_PATTERN = Pattern.compile("(?<name>.*) /at (?<timeRange>.*)");

    /**
     * Constructs an Event from a Scanner with arguments.
     *
     * @param sc The scanner with the remaining text in the message.
     * @return The constructed event.
     * @throws MessagefulException There is an issue with the arguments.
     */
    public static Event fromChat(Scanner sc) throws MessagefulException {
        String rest = sc.hasNextLine() ? sc.nextLine() : "";
        Matcher match = CHAT_PATTERN.matcher(rest);
        if (match.matches()) {
            return new Event(match.group("name"), match.group("timeRange"));
        } else {
            throw new MessagefulException(
                    "Event syntax no match",
                    "Events are added like this: event project meeting /at Mon 2-4pm"
            );
        }
    }

    public static final String TYPE_CODE = "E";

    /**
     * Packs the task's data into a List.
     *
     * @return The packed data.
     */
    @Override
    public List<String> flatPack() {
        List<String> result = new ArrayList<>(super.flatPack());
        result.set(0, TYPE_CODE);
        result.add(timeRange);

        return result;
    }

    /**
     * Unpacks the task's data from a List.
     *
     * @param l The packed data.
     */
    public Event(List<? extends String> l) {
        super(l);
        if (!l.get(0).equals(TYPE_CODE)) {
            throw new IllegalArgumentException("Trying to hydrate non-event as event: " + l);
        }
        this.timeRange = l.get(3);
    }

    @Override
    public String toString() {
        return format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }
}
