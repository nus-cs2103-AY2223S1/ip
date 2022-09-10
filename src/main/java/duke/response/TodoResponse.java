package duke.response;

import duke.DukeException;
import duke.DukeList;
import duke.task.Todo;

/**
 * A DukeResponse for a Todo.
 */
public class TodoResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    /**
     * Constructor for a TodoResponse.
     *
     * @param list The task list.
     * @param data The data for a new Todo.
     */
    public TodoResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public String run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        Todo t = new Todo(data);
        return list.add(t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
