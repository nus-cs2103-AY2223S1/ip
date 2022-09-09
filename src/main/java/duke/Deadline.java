package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has to be done by a certain date and/or time.
 */
public class Deadline extends Task {
    protected String by = "";
    protected LocalDate byDate;
    protected LocalTime byTime;

    /**
     * Constructor for the Deadline class.
     * @param description A string that provides information for the task.
     * @param by A string that provides the deadline that the task has to be completed by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        convertToDateTime(by);
    }

    /**
     * Another constructor for the Deadline class.
     * @param description A string that provides information for the task.
     * @param byDate A LocalDate object that provides information about the date that the task has to be completed by.
     * @param byTime A LocalTime object that provides information about the time that the task has to be completed by.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    private void convertToDateTime(String by) {
        if (by.length() > 10) {
            int spacePos = by.indexOf(" ");
            String date = by.substring(0, spacePos);
            String time = by.substring(spacePos + 1);
            this.byDate = LocalDate.parse(date);
            this.byTime = LocalTime.parse(time);
        } else if (by.contains("-")) {
            this.byDate = LocalDate.parse(by);
        } else if (by.contains(":")) {
            this.byTime = LocalTime.parse(by);
        }
    }

    @Override
    public void update(String input, VBox dialogContainer, Image dukeImage) {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            updateDescription(input);
        } else if (byIndex == 0) {
            updateDateTime(input.substring(4));
        } else {
            String description = input.substring(0, byIndex - 1);
            String dateTime = input.substring(byIndex + 4);

            updateDescription(description);
            updateDateTime(dateTime);
        }
        this.sendTaskUpdatedMessage(dialogContainer, dukeImage);
    }

    private void updateDescription(String description) {
        super.description = description;
    }

    private void updateDateTime(String dateTime) {
        convertToDateTime(dateTime);
    }

    /**
     * Returns the deadline task as a string.
     * @return The deadline task as a string.
     */
    @Override
    public String toString() {
        return ("D | "
                + super.toString()
                + " | "
                + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ((this.byTime == null)
                        ? ""
                        : " | " + this.byTime.toString()));
    }
}
