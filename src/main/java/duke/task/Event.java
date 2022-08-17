package duke.task;

import duke.core.DukeException;
import duke.task.Task;

/**
 * A tasks that starts at a specific time and ends at a specific time e.g., team project meeting on 2/10/2019 2-4pm
 */
public class Event extends Task {


    @Override
    public DukeException invalidParameterError() {
        return new DukeException("No time provided!");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + details + ")";
    }

}
