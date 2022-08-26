package duke.task;
public class Task {
    private String description;
    private boolean isDone;

    public Task(String str) {
        this.description = str;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns true of the description of the task contains the keyword.
     *
     * @param keyword Keyword to be checked.
     * @return A boolean value.
     */
    public boolean matchDescription(String keyword) {
        return this.description.contains(keyword);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

}
