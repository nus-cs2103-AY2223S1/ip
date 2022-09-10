package duke.response;

import duke.DukeList;

/**
 * A DukeResponse that displays the task list.
 */
public class ListResponse extends DukeResponse {
    protected DukeList list;

    /**
     * Constructor for a ListResponse.
     *
     * @param list The task list.
     */
    public ListResponse(DukeList list) {
        this.list = list;
    }

    @Override
    public String run() {
        return this.list.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
