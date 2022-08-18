package pnkp.duke.modules.todos;

import pnkp.duke.MessagefulException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Events - tasks with a time range.
 */
public class Event extends Task{
    private String timeRange;

    /**
     * Constructor
     * @param name The name of the task.
     */
    public Event(String name, String timeRange) {
        this(name, false, timeRange);
    }

    /**
     * Constructor
     * @param name The name of the task
     * @param done Whether the task is done.
     * @param timeRange The time range of the task.
     */
    public Event(String name, boolean done, String timeRange) {
        super(name, done);
        this.timeRange = timeRange;
    }

    //@@author parnikkapore-reused
    // Adapted from https://stackoverflow.com/questions/54593569/#54593895
    private final static Pattern chatPattern = Pattern.compile("(?<name>.*) /at (?<timeRange>.*)");

    /**
     * Constructs an Event from a Scanner with arguments.
     * @param sc The scanner with the remaining text in the message.
     * @return The constructed event.
     * @throws MessagefulException There is an issue with the arguments.
     */
    public static Event fromChat(Scanner sc) throws MessagefulException {
        String rest = sc.hasNextLine() ? sc.nextLine() : "";
        Matcher match = chatPattern.matcher(rest);
        if (match.matches()) {
            return new Event(match.group("name"), match.group("timeRange"));
        } else {
            throw new MessagefulException(
                    "Event syntax no match",
                    "Events are added like this: event project meeting /at Mon 2-4pm"
            );
        }
    }

    @Override
    public String toString() {
        return format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }
}
