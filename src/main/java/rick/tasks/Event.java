package rick.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import rick.RickException;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private LocalDate date;

    /**
     * Constructs an Event object. There are four possible formats for initializing
     * an event:
     * 1. Event task with only description and unprocessed date (completion status
     * defaults to incomplete)
     * 2. Event task with description and unprocessed date and completion status
     * 3. Event task with only the user instruction input
     * 4. Event task with description, processed date object, and
     * completion status
     * 
     * @param description The description of the event.
     * 
     * @param date        The date of the event.
     * 
     * @param isDone      The status of the event.
     */
    public Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructs an Event object.
     * 
     * @param description The description of the event.
     * 
     * @param date        The date of the event.
     * 
     * @param isDone      The status of the event.
     */
    public Event(String description, String date, boolean isDone) {
        super(description, isDone);
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructs an Event object.
     * 
     * @param instruction The instruction to be executed.
     */
    public Event(String instructions) throws RickException {
        super(instructions.split("/at")[0]);
        String date = instructions.split("/at")[1].trim();
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructs an Event object.
     * 
     * @param description The description of the event.
     * @param eventDate   The date of the event.
     * @param isDone      The status of the event.
     */
    public Event(String description, LocalDate eventDate, boolean isDone) {
        super(description, isDone);
        this.date = eventDate;
    }

    /**
     * Validates input to ensure it is correct
     * 
     * @return boolean value of whether command is valid
     */
    public static boolean isValidInput(String instructions) throws RickException {
        if (instructions.isBlank()) {
            throw new RickException("Morty, your input can't be blank!");
        }
        if (!instructions.contains("/at")) {
            throw new RickException("Morty, your input is wrong! Please include the prefix '/at'.");
        }
        if (instructions.split("/at").length < 2) {
            throw new RickException("Morty, your input is wrong! Please include a date input after '/at'!");
        }
        try {
            LocalDate date = LocalDate.parse(instructions.split("/at")[1].trim());
        } catch (IndexOutOfBoundsException e) {
            throw new RickException("Morty, I can't process your date input! Here's the correct date format:\n"
                    + "'YYYY-MM-DD'");
        } catch (Exception e) {
            throw new RickException("Morty, I can't process your date input! Here's the error I found:\n"
                    + e.getMessage() + "\nMake sure your date format is 'YYYY-MM-DD'");
        }
        return true;
    }

    /**
     * Returns the date of the event, in MMM dd yyyy format.
     * 
     * @return The date of the event.
     */
    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    /**
     * Returns the string representation of the event to be output to the user.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDate() + ")";
    }

    /**
     * Returns the string representation of the event to be saved in the data file.
     */
    @Override
    public String save() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + date;
    }

    /**
     * Returns true if the event is equal to the input event. Two events are equal
     * if they have the same description and date.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event) {
            Event other = (Event) obj;
            return this.description.equals(other.description) && this.date.equals(other.date);
        } else {
            return false;
        }
    }
}
