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
     *
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
     *
     * @param description A string that provides information for the task.
     * @param byDate A LocalDate object that provides information about the date that the task has to be completed by.
     * @param byTime A LocalTime object that provides information about the time that the task has to be completed by.
     */
    public Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    /**
     * Converts a string containing date and/or time into a LocalDate and/or LocalTime object,
     * and updates the object's field.
     *
     * @param by The string containing the date and/or time that the deadline task has to be done by.
     */
    private void convertToDateTime(String by) {
        assert by.length() != 0 : "length of 'by' should not be 0";

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

    /**
     * Updates the deadline task with new details contained in the string 'input'.
     *
     * @param input The string containing the new updated information.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    @Override
    public void update(String input, VBox dialogContainer, Image dukeImage) {
        assert input.length() != 0 : "Length of input should not be 0";

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

    /**
     * Updates the description field of the task with the new description.
     *
     * @param description The string containing the new description.
     */
    private void updateDescription(String description) {
        super.description = description;
    }

    /**
     * Updates the date and/or time field(s) of the task with the new date and/or time.
     *
     * @param dateTime The string containing the new date and/or time.
     */
    private void updateDateTime(String dateTime) {
        convertToDateTime(dateTime);
    }

    /**
     * Returns the deadline task as a string.
     *
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
