package pnkp.duke.modules.todos;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class Deadline extends Task{
    private String deadline;

    public Deadline(String name, String deadline) {
        this(name, false, deadline);
    }

    public Deadline(String name, boolean done, String deadline) {
        super(name, done);
        this.deadline = deadline;
    }

    //@@author parnikkapore-reused
    // Adapted from https://stackoverflow.com/questions/54593569/#54593895
    private final static Pattern chatPattern = Pattern.compile("(?<name>.*) /by (?<time>.*)");
    public static Deadline fromChat(Scanner sc) throws IllegalArgumentException {
        String rest = sc.hasNextLine() ? sc.nextLine() : "";
        Matcher match = chatPattern.matcher(rest);
        if (match.matches()) {
            return new Deadline(match.group("name"), match.group("time"));
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
