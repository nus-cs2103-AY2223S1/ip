public class Task {
    private static final String NO_DESC_MESSAGE = "The description of a Task must not be empty.";

    protected String desc;
    protected boolean isDone;

    public Task(String desc) throws DukeException {
        if (desc == null || desc.equals("")) {
            throw new DukeException(Task.NO_DESC_MESSAGE);
        }
        this.desc = desc;
        this.isDone = false;
    }

    public Task(String desc, boolean isDone) throws DukeException {
        if (desc == null || desc.equals("")) {
            throw new DukeException(Task.NO_DESC_MESSAGE);
        }
        this.desc = desc;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[✔]" : "[ ]");
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.desc;
    }

}
