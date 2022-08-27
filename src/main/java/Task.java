public abstract class Task {
    protected String item;
    protected boolean done;

    public Task(String item) {
        this.item = item;
        this.done = false;
    }

    /**
     * The toString method of the Task class.
     *
     * @return The string representation of the Task object.
     */
    @Override
    public String toString() {
        if (!done) {
            return "[ ] " + item;
        } else {
            return "[X] " + item;
        }
    }

    public abstract String toLine();

    public void setDone() {
        this.done = true;
    }

    public void setUnDone() {
        this.done = false;
    }
}
