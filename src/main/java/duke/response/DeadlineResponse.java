package duke.response;

import duke.DukeException;
import duke.DukeList;
import duke.Parser;
import duke.task.Deadline;

/**
 * A DukeResponse for a Deadline.
 */
public class DeadlineResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    /**
     * Constructor for a DeadlineResponse.
     *
     * @param list The task list.
     * @param data The data for a new Deadline.
     */
    public DeadlineResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public String run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        if (!data.contains("/by ")) {
            throw new DukeException("Please enter deadline of task.");
        }

        int splitIndex = data.indexOf("/by ");
        String description = data.substring(0, splitIndex).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String dateTimeStr = data.substring(splitIndex + 3).trim();
        if (dateTimeStr.isEmpty()) {
            throw new DukeException("Please enter deadline of task.");
        }

        Deadline d = new Deadline(description, Parser.strToDate(dateTimeStr));
        return list.add(d);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
