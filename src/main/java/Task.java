public class Task {
    private final String task_description;
    private boolean done;
    private static final String already = "This task is already marked as ";

    Task(String task_description) {
        this.task_description = task_description;
        this.done = false;
    }

    Task(String task_description, boolean done) {
        this.task_description = task_description;
        this.done = done;
    }

    public void doing() throws DukeException {
        if (!this.done) {
            this.done = true;
        } else {
            throw new DukeException(already + "done");
        }
    }

    public void undo() throws DukeException {
        if (this.done) {
            this.done = false;
        } else {
            throw new DukeException(already + "not done");
        }
    }

    public String description() {
        return this.task_description;
    }
    public boolean status() {
        return this.done;
    }

    @Override
    public String toString() {
        String marker;
        if (this.done) {
            marker = "X";
        } else {
            marker = " ";
        }
        return "[" + marker + "] " + this.task_description;
    }
}
