package duke.response;

import duke.DukeException;
import duke.DukeList;
import duke.Storage;

/**
 * A DukeResponse for writing to data file.
 */
public class WriteFileResponse extends DukeResponse {
    protected DukeList list;
    protected Storage storage;

    /**
     * Constructor for a WriteFileResponse.
     * @param list The task list.
     * @param storage The handler for writing task list to storage.
     */
    public WriteFileResponse(DukeList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    @Override
    public void run() throws DukeException {
        super.message("Saving data...");
        storage.write(this.list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
