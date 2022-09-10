package duke.response;

import duke.DukeException;
import duke.DukeList;
import duke.Parser;
import duke.task.Event;

/**
 * A DukeResponse for an Event.
 */
public class EventResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    /**
     * Constructor for an EventResponse.
     *
     * @param list The task list.
     * @param data The data for a new Event.
     */
    public EventResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public String run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        if (!data.contains("/at ")) {
            throw new DukeException("Please enter event time.");
        }

        int splitIndex = data.indexOf("/at ");
        String description = data.substring(0, splitIndex).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String dateTimeStr = data.substring(splitIndex + 3).trim();
        if (dateTimeStr.isEmpty()) {
            throw new DukeException("Please enter event time.");
        }

        Event e = new Event(description, Parser.strToDate(dateTimeStr));
        return list.add(e);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
