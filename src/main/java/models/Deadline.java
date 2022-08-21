package models;

import utils.DukeFormatter;

import java.time.LocalDate;

/**
 * Encapsulates a task that needs to be done before a specific date/time,
 * e.g., submit report by 11/10/2019 5pm
 *
 * @author Emily Ong Hui Qi
 */

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return TaskType.DEADLINE.toString();
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), DukeFormatter.formatDate(this.deadline));
    }
}
