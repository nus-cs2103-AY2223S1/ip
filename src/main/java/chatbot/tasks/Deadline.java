package chatbot.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline class is a subclass of Task. It represents
 * a task in real life that has a deadline associated.
 */
public class Deadline extends Task {
    public static final String TYPE = "deadline";

    private LocalDate date;

    /**
     * The constructor for a Deadline task.
     *
     * @param taskName Name of the Deadline task.
     * @param date Date of the Deadline.
     */
    public Deadline(String taskName, LocalDate date) {
        super(taskName);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String save() {
        return "D | " + this.getStatus() + " | " + this.getTaskName() + " | " + this.date;
    }
}
