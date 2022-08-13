public class Task {

    private final String name;
    private boolean done;

    public Task(String name, boolean done) throws TaskNoNameException {

        if (name.isEmpty()) {
            throw new TaskNoNameException("The name for a task cannot be empty.");
        }

        this.name = name;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "[" + (this.done ? "X" : " ") + "] " + this.name;
    }

}
