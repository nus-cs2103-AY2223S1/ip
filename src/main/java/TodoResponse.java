public class TodoResponse extends DukeResponse {
    protected DukeList list;
    protected String data;

    public TodoResponse(DukeList list, String data) {
        this.list = list;
        this.data = data;
    }

    @Override
    public void run() throws DukeException {
        if (data.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        Todo t = new Todo(data);
        super.message(list.add(t));
    }
}