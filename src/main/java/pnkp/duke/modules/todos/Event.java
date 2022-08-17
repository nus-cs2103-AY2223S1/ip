package pnkp.duke.modules.todos;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class Event extends Task{
    private String timeRange;

    public Event(String name, String timeRange) {
        this(name, false, timeRange);
    }

    public Event(String name, boolean done, String timeRange) {
        super(name, done);
        this.timeRange = timeRange;
    }

    //@@author parnikkapore-reused
    // Adapted from https://stackoverflow.com/questions/54593569/#54593895
    private final static Pattern chatPattern = Pattern.compile("(?<name>.*) /at (?<timeRange>.*)");
    public static Event fromChat(Scanner sc) throws IllegalArgumentException {
        String rest = sc.hasNextLine() ? sc.nextLine() : "";
        Matcher match = chatPattern.matcher(rest);
        if (match.matches()) {
            return new Event(match.group("name"), match.group("timeRange"));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return format("[E]%s (at: %s)", super.toString(), this.timeRange);
    }
}
