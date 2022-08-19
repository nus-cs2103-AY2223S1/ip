import java.util.Scanner;
import java.util.NoSuchElementException;

public class StorageParser {
    private static final String DELIMITER = "\\s*[|]\\s*";
    private static final String TODO_INDICATOR = "T";
    private static final String DEADLINE_INDICATOR = "D";
    private static final String EVENT_INDICATOR = "E";

    static Scanner createScanner(String input) {
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter(DELIMITER);
        return scanner;
    }

    public static Task parse(String input) throws DukeException {
        try (Scanner scanner = createScanner(input)) {
            String taskIndicator = scanner.next();
            Task task;
            switch (taskIndicator) {
            case (TODO_INDICATOR):
                task = parseTodo(input);
                break;
            case (DEADLINE_INDICATOR):
                task = parseDeadline(input);
                break;
            case (EVENT_INDICATOR):
                task = parseEvent(input);
                break;
            default:
                throw new DukeException("Unknown task");
            }
            return task;
        }
    }

    private static boolean intToBoolean(int number) {
        return number != 0;
    }

    static Todo parseTodo(String input) throws DukeException {
        try (Scanner lineScanner = createScanner(input)) {
            lineScanner.next();
            boolean status = intToBoolean(lineScanner.nextInt());
            String description = lineScanner.next();
            return new Todo(description, status);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format read when loading todo!", e);
        }
    }

    static Deadline parseDeadline(String input) throws DukeException {
        try (Scanner lineScanner = createScanner(input)) {
            lineScanner.next();
            boolean status = intToBoolean(lineScanner.nextInt());
            String description = lineScanner.next();
            String by = lineScanner.next();
            return new Deadline(description, status, by);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format read when adding deadline!", e);
        }
    }

    static Event parseEvent(String input) throws DukeException {
        try (Scanner lineScanner = createScanner(input)) {
            lineScanner.next();
            boolean status = intToBoolean(lineScanner.nextInt());
            String description = lineScanner.next();
            String at = lineScanner.next();
            return new Event(description, status, at);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format read when adding event!", e);
        }
    }

}
