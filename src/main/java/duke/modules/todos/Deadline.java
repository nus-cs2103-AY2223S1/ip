package duke.modules.todos;

import duke.MessagefulException;
import duke.util.NaturalDateParser;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Deadlines - tasks with a due date.
 */
public class Deadline extends Task {
    private Instant deadline;

    /**
     * Constructor
     *
     * @param name The name of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String name, Instant deadline) {
        this(name, false, deadline);
    }

    /**
     * Constructor
     *
     * @param name     The name of the task
     * @param done     Whether the task is done.
     * @param deadline The deadline of the task.
     */
    public Deadline(String name, boolean done, Instant deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    //@@author parnikkapore-reused
    // Adapted from https://stackoverflow.com/questions/54593569/#54593895
    private final static Pattern CHAT_PATTERN = Pattern.compile("(?<name>.*) /by (?<time>.*)");

    /**
     * Constructs a Deadline from a Scanner with arguments.
     *
     * @param sc The scanner with the remaining text in the message.
     * @return The constructed deadline.
     * @throws MessagefulException There is an issue with the arguments.
     */
    public static Deadline fromChat(Scanner sc) throws MessagefulException {
        String rest = sc.hasNextLine() ? sc.nextLine() : "";
        Matcher match = CHAT_PATTERN.matcher(rest);
        if (match.matches()) {
            try {
                return new Deadline(
                        match.group("name"),
                        NaturalDateParser.parse(match.group("time")));
            } catch (NaturalDateParser.DateNotFoundException e) {
                throw new MessagefulException(
                        "datetime parse failure" + e,
                        e.getParsedString() + " doesn't look like a date and time to me...");
            }
        } else {
            throw new MessagefulException(
                    "Deadline syntax no match",
                    "Deadlines are added like this: deadline return book /by Sunday"
            );
        }
    }

    public static final String TYPE_CODE = "D";

    /**
     * Packs the task's data into a List.
     *
     * @return The packed data.
     */
    @Override
    public List<String> flatPack() {
        List<String> result = new ArrayList<>(super.flatPack());
        result.set(0, TYPE_CODE);
        result.add(deadline.toString());

        return result;
    }

    /**
     * Unpacks the task's data from a List.
     *
     * @param l The packed data.
     */
    public Deadline(List<? extends String> l) {
        super(l);
        if (!l.get(0).equals(TYPE_CODE)) {
            throw new IllegalArgumentException("Trying to hydrate non-deadline as deadline: " + l);
        }
        this.deadline = Instant.parse(l.get(3));
    }

    @Override
    public String toString() {
        //@@author parnikkapore-reused
        // Adapted from https://java2blog.com/format-instant-to-string-java/
        DateTimeFormatter format = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM)
                .withZone(ZoneId.systemDefault());
        return format(
                "[D]%s (by: %s)",
                super.toString(),
                format.format(this.deadline));
    }
}
