package duke.response;

import duke.DukeException;
import duke.DukeList;

public class MarkResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    public MarkResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("Please enter duke.task to mark as done.");
        }

        int index = Integer.parseInt(data.trim());
        super.message(list.done(index));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
