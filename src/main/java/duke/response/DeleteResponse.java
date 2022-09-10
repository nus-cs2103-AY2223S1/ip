package duke.response;

import duke.DukeException;
import duke.DukeList;

/**
 * A DukeResponse for a task deletion.
 */
public class DeleteResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    /**
     * Constructor for a DeleteResponse.
     *
     * @param list The task list.
     * @param data The data for task deletion.
     */
    public DeleteResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public String run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("Please enter task to delete.");
        }

        int index = Integer.parseInt(data.trim());
        return list.delete(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
