package caca.tasks;

import caca.exceptions.InvalidDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class represents tasks that start at a specific time and ends at a specific time.
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructor for creating an event.
     * @param description caca.tasks.Task description from user input.
     * @param dateTimeInput caca.tasks.Event date/time from user input.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    public Event(String description, String dateTimeInput) throws InvalidDateException {
        super(description);

        try {
            // Adapted from 3. LocalDate parse() method and 4. LocalDateTime parse() method of
            // https://codegym.cc/groups/posts/parse-methods-in-java
            // and subsection "Using dates/times in Java" of section "Duke Level-8: Dates and Times" from
            // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            at = LocalDateTime.parse(dateTimeInput, formatter);

        } catch (DateTimeParseException e) {
            throw new InvalidDateException("You have keyed in an invalid date and time!\n"
                    + "Please specify date and time in the format: dd/MM/yyyy HHmm\n"
                    + "E.g. 24/08/2022 2359");
        }

    }

    /**
     * Constructor for creating an event with given isDone status.
     * @param description caca.tasks.Task description from user input.
     * @param dateTimeInput caca.tasks.Event date/time from user input.
     * @param isDone True if event is marked as done; false otherwise.
     * @throws InvalidDateException If date entered by user is not in the specified format.
     */
    public Event (String description, String dateTimeInput, boolean isDone) throws InvalidDateException {
        // Explicit constructor invocation adapted from
        // https://docs.oracle.com/javase/tutorial/java/javaOO/thiskey.html
        this(description, dateTimeInput);
        this.isDone = isDone;
    }

    /**
     * Displays the task type of event as E.
     * @return E.
     */
    @Override
    public String taskType() {
        return "E";
    }

    /**
     * Displays the event with its type, status (done or undone), description and time.
     * @return caca.tasks.Task type, status, description and time.
     */
    @Override
    public String toString() {
        // Adapted from 3. LocalDate parse() method and 4. LocalDateTime parse() method of
        // https://codegym.cc/groups/posts/parse-methods-in-java
        // and subsection "Using dates/times in Java" of section "Duke Level-8: Dates and Times" from
        // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
        String newDateTime = at.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return String.format("%s (at: %s)", super.toString(), newDateTime);
    }

    /**
     * Formats event in a file.
     * @return caca.tasks.Event with task type, status, description and time.
     */
    @Override
    public String toFileFormat() {
        // Adapted from 3. LocalDate parse() method and 4. LocalDateTime parse() method of
        // https://codegym.cc/groups/posts/parse-methods-in-java
        // and subsection "Using dates/times in Java" of section "Duke Level-8: Dates and Times" from
        // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
        String formattedDateTime = at.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        return String.format("E | %s | %s | %s", this.getStatusIcon(), this.description, formattedDateTime);
    }

}
