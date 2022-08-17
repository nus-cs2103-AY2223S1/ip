public class UnmarkResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    public UnmarkResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() throws DukeException {
        int index = Integer.parseInt(data.trim());
        super.message(list.undone(index));
    }
}