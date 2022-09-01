package duke.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import duke.exceptions.InvalidDateTimeException;
import duke.exceptions.InvalidTaskSpecificationException;

/**
 * The type Task.
 */
public class Task {

    /**
     * The constant FORMATTER.
     */
    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm");
    protected static final String NULL_SYMBOL = "$_$";
    private final String text;
    private Boolean isComplete;

    /**
     * Instantiates a new Task.
     *
     * @param text
     *            the text
     */
    protected Task(String text) {
        this.text = text;
        this.isComplete = false;
    }

    /**
     * Sets the task to be complete.
     */
    public void setComplete() {
        this.isComplete = true;
    }

    /**
     * Sets the task to be incomplete.
     */
    public void setIncomplete() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        return this.isComplete
                ? String.format("[X] %s", this.text)
                : String.format("[ ] %s", this.text);
    }

    private static LocalDateTime interpretLocalDateTime(String input)
            throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    /**
     * Of task.
     *
     * @param type
     *            the type
     * @param done
     *            the done
     * @param text
     *            the text
     * @param dateTimeInput
     *            the date time input
     * @return the task
     * @throws InvalidDateTimeException
     *             the invalid date time exception
     * @throws InvalidTaskSpecificationException
     *             the invalid task specification exception
     */
    public static Task of(
            String type,
            String done,
            String text,
            String dateTimeInput)
            throws InvalidDateTimeException, InvalidTaskSpecificationException {
        Boolean isEvent = Objects.equals(type, "E");
        Boolean isDeadline = Objects.equals(type, "D");
        Boolean isTodo = Objects.equals(type, "T");
        if (isEvent || isDeadline || isTodo) {
            if (isEvent || isDeadline) {
                try {
                    LocalDateTime dateTime = interpretLocalDateTime(
                            dateTimeInput);
                    if (isEvent) {
                        return Event.of(done, text, dateTime);
                    }
                    return Deadline.of(done, text, dateTime);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(
                            "Must have valid date time!");
                }
            }
            return Todo.of(done, text);
        } else {
            throw new InvalidTaskSpecificationException(
                    "Impossible for task to be not e d or t");
        }
    }

    /**
     * Export the export string.
     *
     * @return the string
     */
    public String exportString() {
        return String.format(
                "%s%d%s%s%s",
                "@@@",
                this.isComplete ? 1 : 0,
                "@@@",
                this.text,
                "@@@");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return text.equals(task.text) && isComplete.equals(task.isComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, isComplete);
    }

    /**
     * Is before boolean.
     *
     * @param dateTime
     *            the date time
     * @return the boolean
     */
    public Boolean isBefore(LocalDateTime dateTime) {
        return true;
    }

    /**
     * Is after boolean.
     *
     * @param dateTime
     *            the date time
     * @return the boolean
     */
    public Boolean isAfter(LocalDateTime dateTime) {
        return true;
    }

    /**
     * The textContains function checks if the text of a task contains any of the
     * search terms.
     *
     * @param String...
     *            Pass in a variable number of strings to the function
     * @return A boolean value
     */
    public Boolean textContains(String... searchTerms) {
        for (String searchTerm : searchTerms) {
            return this.text.contains(searchTerm);
        }
        return false;
    }
}
