package models;

import utils.DukeFormatter;

import java.time.LocalDate;

/**
 * Encapsulates a task that starts at a specific time and ends at a specific time,
 * e.g., team project meeting on 2/10/2019 2-4pm
 *
 * @author Emily Ong Hui Qi
 */

public class Event extends Task {
    protected LocalDate datetime;

    public Event(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    @Override
    public String getTaskTypeIcon() {
        return TaskType.EVENT.toString();
    }

    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(), DukeFormatter.formatDate(this.datetime));
    }
}
