public class DeadlineResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    public DeadlineResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        if (!data.contains("/by")) {
            throw new DukeException("Please enter deadline of task.");
        }

        int splitIndex = data.indexOf("/");
        String description = data.substring(0, splitIndex).trim();
        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        String dateTime = data.substring(splitIndex + 3).trim();
        if (dateTime.isEmpty()) {
            throw new DukeException("Please enter deadline of task.");
        }

        Deadline d = new Deadline(description, dateTime);
        super.message(list.add(d));
    }
}