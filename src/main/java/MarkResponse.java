public class MarkResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    public MarkResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() throws DukeException {
        int index = Integer.parseInt(data.trim());
        super.message(list.done(index));
    }
}