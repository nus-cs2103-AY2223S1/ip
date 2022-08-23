package duke.response;

import duke.DukeException;
import duke.DukeList;

public class UnmarkResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    public UnmarkResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("Please enter duke.task to mark as undone.");
        }

        int index = Integer.parseInt(data.trim());
        super.message(list.undone(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}