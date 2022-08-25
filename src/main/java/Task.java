public class Task {
    private final String task_description;
    private boolean isDone;
    private static final String already = "This task is already marked as ";

    Task(String task_description) {
        this.task_description = task_description;
        this.isDone = false;
    }

    Task(String task_description, boolean isDone) {
        this.task_description = task_description;
        this.isDone = isDone;
    }

    public void doing() throws DukeException {
        if (!this.isDone) {
            this.isDone = true;
        } else {
            throw new DukeException(already + "done");
        }
    }

    public void undo() throws DukeException {
        if (this.isDone) {
            this.isDone = false;
        } else {
            throw new DukeException(already + "not done");
        }
    }

    public String description() {
        return this.task_description;
    }
    public boolean status() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String marker;
        if (this.isDone) {
            marker = "X";
        } else {
            marker = " ";
        }
        return "[" + marker + "] " + this.task_description;
    }
}
