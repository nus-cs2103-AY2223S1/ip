package duke.modules.todos;

import duke.MessagefulException;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

/**
 * Deadlines - tasks with a due date.
 */
 public class Deadline extends Task{
    private LocalDateTime deadline;

    /**
     * Constructor
     * @param name The name of the task.
     */
    public Deadline(String name, LocalDateTime deadline) {
        this(name, false, deadline);
    }

    /**
     * Constructor
     * @param name The name of the task
     * @param done Whether the task is done.
     * @param deadline The deadline of the task.
     */
    public Deadline(String name, boolean done, LocalDateTime deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    //@@author parnikkapore-reused
    // Adapted from https://stackoverflow.com/questions/54593569/#54593895
    private final static Pattern chatPattern = Pattern.compile("(?<name>.*) /by (?<time>.*)");

    /**
     * Constructs a Deadline from a Scanner with arguments.
     * @param sc The scanner with the remaining text in the message.
     * @return The constructed deadline.
     * @throws MessagefulException There is an issue with the arguments.
     */
    public static Deadline fromChat(Scanner sc) throws MessagefulException {
        String rest = sc.hasNextLine() ? sc.nextLine() : "";
        Matcher match = chatPattern.matcher(rest);
        if (match.matches()) {
            try {
                return new Deadline(
                        match.group("name"),
                        LocalDateTime.parse(match.group("time")));
            } catch (DateTimeParseException e) {
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

    public static final String typeCode = "D";

    @Override
    public List<String> flatPack() {
        List<String> result = new ArrayList<>(super.flatPack());
        result.set(0, typeCode);
        result.add(deadline.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        return result;
    }

    public Deadline(List<? extends String> l) {
        super(l);
        if (!l.get(0).equals(typeCode)) {
            throw new IllegalArgumentException("Trying to hydrate non-deadline as deadline: " + l);
        }
        this.deadline = LocalDateTime.parse(l.get(3), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    @Override
    public String toString() {
        return format(
                "[D]%s (by: %s)",
                super.toString(),
                this.deadline.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
    }
}
