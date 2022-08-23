package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import exceptions.InvalidDateTimeException;
import exceptions.InvalidTaskSpecificationException;

public class Task {
    private final String text;
    private Boolean isComplete;
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
            "dd-MM-yyyy HH:mm");

    public Task(String text) {
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

    private static LocalDateTime interpretLocalDateTime(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    public static Task of(String type, String done, String text, String dateTimeInput)
            throws InvalidDateTimeException, InvalidTaskSpecificationException {
        Boolean isEvent = Objects.equals(type, "E");
        Boolean isDeadline = Objects.equals(type, "D");
        Boolean isTodo = Objects.equals(type, "T");
        if (isEvent || isDeadline || isTodo) {
            if (isEvent || isDeadline) {
                try {
                    LocalDateTime dateTime = interpretLocalDateTime(dateTimeInput);
                    if (isEvent) {
                        return Event.of(
                                done,
                                text,
                                dateTime);
                    }
                    return Deadline.of(
                            done,
                            text,
                            dateTime);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(
                            "Must have valid date time!");
                }
            }
            return Todo.of(
                    done,
                    text);
        } else {
            throw new InvalidTaskSpecificationException(
                    "Impossible for task to be not e d or t");
        }

    }

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
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return text.equals(task.text) && isComplete.equals(task.isComplete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, isComplete);
    }

    public Boolean isBefore(LocalDateTime dateTime) {
        return true;
    }

    public Boolean isAfter(LocalDateTime dateTime) {
        return true;
    }
}
