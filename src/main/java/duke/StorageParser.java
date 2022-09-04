package duke;

import java.util.NoSuchElementException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class to parse text in storage file.
 */
public abstract class StorageParser {
    private static final String DELIMITER = "\\s*[|]\\s*";
    private static final String INDICATOR_TODO = "T";
    private static final String INDICATOR_DEADLINE = "D";
    private static final String INDICATOR_EVENT = "E";
    private static final String MESSAGE_UNKNOWN_TASK = "Unknown task";
    private static final String ERROR_MESSAGE_READ_STORAGE_FILE = "Error reading storage file";
    private static final String ERROR_MESSAGE_TODO_FORMAT_INVALID = "Invalid format read when loading todo!";
    private static final String ERROR_MESSAGE_DEADLINE_FORMAT_INVALID = "Invalid format read when adding deadline!";
    private static final String ERROR_MESSAGE_EVENT_FORMAT_INVALID = "Invalid format read when adding event!";

    private static Scanner createScanner(String input) {
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter(DELIMITER);
        return scanner;
    }

    /**
     * Parses a line of text in storage file into a Task.
     *
     * @param line Line of text in storage file format.
     * @return Task that was parsed from line.
     * @throws DukeException When error parsing the text.
     */
    public static Task parse(String line) throws DukeException {
        try (Scanner scanner = createScanner(line)) {
            String taskIndicator = scanner.next();
            Task task;
            switch (taskIndicator) {
            case (INDICATOR_TODO):
                task = parseTodo(line);
                break;
            case (INDICATOR_DEADLINE):
                task = parseDeadline(line);
                break;
            case (INDICATOR_EVENT):
                task = parseEvent(line);
                break;
            default:
                throw new DukeException(MESSAGE_UNKNOWN_TASK);
            }
            return task;
        } catch (NoSuchElementException e) {
            throw new DukeException(ERROR_MESSAGE_READ_STORAGE_FILE);
        }
    }

    private static boolean convertIntToBoolean(int number) {
        return number != 0;
    }

    /**
     * Parses line of text into a Todo Task.
     *
     * @param line Line of text in storage file format.
     * @return Todo that was parsed from line.
     * @throws DukeException When error parsing the text.
     */
    static Todo parseTodo(String line) throws DukeException {
        try (Scanner lineScanner = createScanner(line)) {
            lineScanner.next();
            boolean status = convertIntToBoolean(lineScanner.nextInt());
            String description = lineScanner.next();
            return new Todo(description, status);
        } catch (NoSuchElementException e) {
            throw new DukeException(ERROR_MESSAGE_TODO_FORMAT_INVALID, e);
        }
    }

    /**
     * Parses line of text into a Deadline Task.
     *
     * @param line Line of text in storage file format.
     * @return Deadline that was parsed from line.
     * @throws DukeException When error parsing the text.
     */
    static Deadline parseDeadline(String line) throws DukeException {
        try (Scanner lineScanner = createScanner(line)) {
            lineScanner.next();
            boolean status = convertIntToBoolean(lineScanner.nextInt());
            String description = lineScanner.next();
            String by = lineScanner.next();
            return new Deadline(description, status, by);
        } catch (NoSuchElementException e) {
            throw new DukeException(ERROR_MESSAGE_DEADLINE_FORMAT_INVALID, e);
        }
    }

    /**
     * Parses line of text into an Event Task.
     *
     * @param line Line of text in storage file format.
     * @return Event that was parsed from line.
     * @throws DukeException When error parsing the text.
     */
    static Event parseEvent(String line) throws DukeException {
        try (Scanner lineScanner = createScanner(line)) {
            lineScanner.next();
            boolean status = convertIntToBoolean(lineScanner.nextInt());
            String description = lineScanner.next();
            String at = lineScanner.next();
            return new Event(description, status, at);
        } catch (NoSuchElementException e) {
            throw new DukeException(ERROR_MESSAGE_EVENT_FORMAT_INVALID, e);
        }
    }

}
