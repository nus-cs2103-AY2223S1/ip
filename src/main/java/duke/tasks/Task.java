package duke.tasks;

public abstract class Task {

    private final String name;
    private boolean isMarked;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        isMarked = true;
    }

    public void unmark() {
        isMarked = false;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "] " + name;
    }

    /**
     * Generates the string representing this task, to be used for storing
     * in the save file.
     * @return The data string representing this task.
     */
    public abstract String toDataString();

}
