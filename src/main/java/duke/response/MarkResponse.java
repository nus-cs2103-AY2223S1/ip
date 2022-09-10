package duke.response;

import duke.DukeException;
import duke.DukeList;

/**
 * A DukeResponse to mark a Task as done.
 */
public class MarkResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    /**
     * Constructor for a MarkResponse.
     *
     * @param list The task list.
     * @param data The data for marking Task as done.
     */
    public MarkResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public String run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("Please enter task to mark as done.");
        }

        int index = Integer.parseInt(data.trim());
        return list.done(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
