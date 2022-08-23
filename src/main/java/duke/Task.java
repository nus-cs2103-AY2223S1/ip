package duke;

public abstract class Task {
    private String name = null;
    private boolean done = false;

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public void addName(String name) throws DukeException {
        this.name = name;
    }

    public String getTask() { return String.format("%c | %s", (done == true ? '1' : '0'), this.name); };

    public String getStatus() {
        return String.format("[%c] %s", (done == true ? 'X' : ' '), this.name);
    }

}
