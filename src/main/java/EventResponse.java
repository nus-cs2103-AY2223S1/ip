public class EventResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    public EventResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        if (!data.contains("/at")) {
            throw new DukeException("Please enter event time.");
        }

        int splitIndex = data.indexOf("/");
        String description = data.substring(0, splitIndex).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        String dateTime = data.substring(splitIndex + 3).trim();
        if (dateTime.isEmpty()) {
            throw new DukeException("Please enter event time.");
        }

        Event e = new Event(description, dateTime);
        super.message(list.add(e));
    }
}