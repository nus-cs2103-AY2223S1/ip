package rick.tasks;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import rick.RickException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructs a Deadline object. There are four possible formats for
     * initializing a deadline:
     * 1. Deadline task with only description and unprocessed date and time
     * (completion status defaults to incomplete)
     * 2. Deadline task with description and unprocessed date and time and
     * completion status
     * 3. Deadline task with only the user instruction input
     * 4. Deadline task with description, processed datetime object, and completion
     * status
     * 
     * @param description The description of the deadline.
     * 
     * @param deadline    The deadline of the deadline.
     * 
     * @param date        The date of the deadline.
     * 
     * @param time        The time of the deadline.
     * 
     * @param datetime    The datetime of the deadline.
     * 
     * @param isDone      The status of the deadline.
     */
    public Deadline(String description, String date, String time) {
        super(description);
        this.deadline = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
    }

    /**
     * Constructs a Deadline object.
     * 
     * @param description The description of the deadline.
     * 
     * @param date        The date of the deadline.
     * 
     * @param time        The time of the deadline.
     * 
     * @param isDone      The status of the deadline.
     */
    public Deadline(String description, String date, String time, boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));
    }

    /**
     * Constructs a Deadline object.
     * 
     * @param instructions The user instruction input.
     */
    public Deadline(String instructions) throws RickException {
        super(instructions.split("/by")[0]);
        String[] dateAndTime = instructions.split("/by")[1].split(" ");
        this.deadline = LocalDateTime.of(LocalDate.parse(dateAndTime[1]), LocalTime.parse(dateAndTime[2]));
    }

    /**
     * Constructs a Deadline object.
     * 
     * @param description The description of the deadline.
     * 
     * @param datetime    The datetime of the deadline.
     * 
     * @param isDone      The status of the deadline.
     */
    public Deadline(String description, LocalDateTime dateTime, boolean isDone) {
        super(description, isDone);
        this.deadline = dateTime;
    }

    /***
     * Validates input to ensure it is correct
     * 
     * @return boolean value of whether command is valid
     */
    public static boolean isValidInput(String instructions) throws RickException {
        if (instructions.isBlank()) {
            throw new RickException("Morty, your input can't be blank!");
        }
        if (!instructions.contains("/by")) {
            throw new RickException("Morty, your input is wrong! Please include the prefix '/by'.");
        }
        if (instructions.split("/by").length < 2) {
            throw new RickException("Morty, your input is wrong! Please include a datetime input after '/by'!");
        }
        try {
            String[] dateAndTime = instructions.split("/by")[1].split(" ");
            LocalDateTime deadline = LocalDateTime.of(LocalDate.parse(dateAndTime[1]), LocalTime.parse(dateAndTime[2]));
        } catch (IndexOutOfBoundsException e) {
            throw new RickException("Morty, I can't process your date input! Here's the correct datetime format:\n"
                    + "'YYYY-MM-DD HH:mm'");
        } catch (Exception e) {
            throw new RickException("Morty, I can't process your date input! Here's the error I found:\n"
                    + e.getMessage() + "\n\nMake sure your datetime format is 'YYYY-MM-DD HH:mm'");
        }
        return true;
    }

    /**
     * Returns the deadline of the deadline.
     * 
     * @return The deadline of the deadline.
     */
    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Returns the output text representation of the deadline.
     * 
     * @return The output text representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + getDeadline() + ")";
    }

    /**
     * Returns the save text representation of the deadline.
     * 
     * @return The save text representation of the deadline.
     */
    @Override
    public String save() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + deadline;
    }

    /**
     * Returns true if the deadline is equal to the other deadline.
     * A deadline is equal to another deadline if they have the same description and
     * deadline.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return this.description.equals(deadline.description) && this.deadline.equals(deadline.deadline);
        } else {
            return false;
        }
    }
}
