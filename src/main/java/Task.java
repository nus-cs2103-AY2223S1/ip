public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        if (description.equals("blah")) {
            throw new DukeException("I blah don't blah know blah ... blah ... zzz\n");
        } else if (description.equals("todo") || description.equals("deadline")
                || description.equals("event") || description.equals("mark")
                || description.equals("unmark")) {
            throw new DukeException("Need more details for your " + description + "!\n");
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markTaskAsDone() {
        this.isDone = true;
    }

    public void markTaskAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
