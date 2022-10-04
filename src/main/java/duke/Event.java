package duke;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that has a date and/or time.
 */
public class Event extends Task {
    protected String at = "";
    protected LocalDate atDate;
    protected LocalTime atTime;

    /**
     * Constructor for the Event class.
     *
     * @param description A string that provides information for the event.
     * @param at A string that provides information about when the event is.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        convertToDateTime(at);
    }

    /**
     * Another constructor for the Event class.
     *
     * @param description A string that provides information for the event.
     * @param atDate A LocalDate object that provides information about the date of the event.
     * @param atTime A LocalTime object that provides information about the time of the event.
     */
    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    /**
     * Converts a string containing date and/or time into a LocalDate and/or LocalTime object,
     * and updates the object's field.
     *
     * @param at The string containing the date and/or time for the event task.
     */
    private void convertToDateTime(String at) {
        assert at.length() != 0 : "length of 'at' should not be 0";

        if (at.length() > 10) {
            int spacePos = at.indexOf(" ");
            String date = at.substring(0, spacePos);
            String time = at.substring(spacePos + 1);
            this.atDate = LocalDate.parse(date);
            this.atTime = LocalTime.parse(time);
        } else if (at.contains("-")) {
            this.atDate = LocalDate.parse(at);
        } else if (at.contains(":")) {
            this.atTime = LocalTime.parse(at);
        }
    }

    /**
     * Updates the event task with new details contained in the string 'input'.
     *
     * @param input The string containing the new updated information.
     * @param dialogContainer The VBox object that contains the chat messages and images.
     * @param dukeImage The image of Duke.
     */
    @Override
    public void update(String input, VBox dialogContainer, Image dukeImage) {
        assert input.length() != 0 : "Length of input should not be 0";

        int byIndex = input.indexOf("/at");
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
     * Returns the event as a string.
     * @return The event as a string.
     */
    @Override
    public String toString() {
        return ("E | "
                + super.toString()
                + " | "
                + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ((this.atTime == null)
                        ? ""
                        : " | " + this.atTime.toString()));
    }
}
