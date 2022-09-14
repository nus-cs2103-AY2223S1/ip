package duke.response;

import duke.DukeException;
import duke.DukeList;

/**
 * A DukeResponse that sorts then displays the list.
 */
public class SortResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    /**
     * Constructor for a SortResponse.
     *
     * @param list The task list.
     * @param data The data for sorting.
     */
    public SortResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public String run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("Please enter sort criteria.");
        }

        return list.sort(data);
    }

    @Override
    public boolean hasModifiedList() {
        return true;
    }
}
