public class ReadFileResponse extends DukeResponse {
    protected DukeList list;

    public ReadFileResponse(DukeList list) {
        this.list = list;
    }

    @Override
    public void run() throws DukeException {
        list.read();
    }
}