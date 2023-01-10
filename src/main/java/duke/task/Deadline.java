package duke.task;

import duke.core.DukeException;

/**
 * A task that needs to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {

    @Override
    public DukeException invalidParameterError() {
        return new DukeException("No deadline given!");
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.Deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedDetails() + ")";
    }
}
