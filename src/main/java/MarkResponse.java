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
            throw new DukeException("Please enter task to mark as done.");
        }

        int index = Integer.parseInt(data.trim());
        super.message(list.done(index));
    }
}