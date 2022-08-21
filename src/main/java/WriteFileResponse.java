public class WriteFileResponse extends DukeResponse {
    protected DukeList list;

    public WriteFileResponse(DukeList list) {
        this.list = list;
    }

    @Override
    public void run() throws DukeException {
        super.message("Saving data...");
        list.write();
    }
}