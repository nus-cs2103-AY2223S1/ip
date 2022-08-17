import java.util.NoSuchElementException;
import java.util.Scanner;

public abstract class CommandParser {
    private static final String DEADLINE_INDICATOR_PATTERN = "\\s*/by\\s*";
    private static final String EVENT_INDICATOR_PATTERN = "\\s*/at\\s*";

    static Todo parseTodo(String input) throws DukeException {
        return new Todo(input);
    }

    static Deadline parseDeadline(String input) throws DukeException {
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(DEADLINE_INDICATOR_PATTERN)) {
            String description = lineScanner.next();
            String by = lineScanner.next();
            return new Deadline(description, by);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format for adding deadline!", e);
        }
    }

    static Event parseEvent(String input) throws DukeException {
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(EVENT_INDICATOR_PATTERN)) {
            String description = lineScanner.next();
            String at = lineScanner.next();
            return new Event(description, at);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format for adding event!", e);
        }
    }
}
