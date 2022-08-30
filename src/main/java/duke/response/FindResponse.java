package duke.response;

import duke.DukeException;
import duke.DukeList;

/**
 * A DukeResponse that finds a search term and returns results.
 */
public class FindResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    /**
     * Constructor for a FindResponse.
     *
     * @param list The task list.
     * @param data The data for searching.
     */
    public FindResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public String run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("Please enter search term.");
        }

        return list.search(data);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
