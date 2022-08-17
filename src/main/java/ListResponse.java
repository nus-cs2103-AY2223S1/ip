public class ListResponse extends DukeResponse {
    protected DukeList list;

    public ListResponse(DukeList list) {
        this.list = list;
    }

    @Override
    public void run() {
        super.message(this.list.toString());
    }
}