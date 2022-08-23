public class WriteFileResponse extends DukeResponse {
    protected DukeList list;
    protected Storage storage;

    public WriteFileResponse(DukeList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    @Override
    public void run() throws DukeException {
        super.message("Saving data...");
        storage.write(this.list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}