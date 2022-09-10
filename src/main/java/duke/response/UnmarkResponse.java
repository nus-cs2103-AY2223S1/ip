package duke.response;

import duke.DukeException;
import duke.DukeList;

/**
 * A DukeResponse to mark a Task as not done.
 */
public class UnmarkResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    /**
     * Constructor for an UnmarkResponse.
     *
     * @param list The task list.
     * @param data The data for marking Task as not done.
     */
    public UnmarkResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public String run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("Please enter task to mark as undone.");
        }

        int index = Integer.parseInt(data.trim());
        return list.undone(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
