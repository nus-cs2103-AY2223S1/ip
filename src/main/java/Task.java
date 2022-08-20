abstract public class Task {
    private String title;
    private boolean isCompleted = false;

    Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets whether the task is completed.
     * @param completed the task status
     * @return true if the status is modified, false otherwise
     */
    public boolean setCompleted(boolean completed) {
        if (completed == this.isCompleted) {
            return false;
        }
        this.isCompleted = completed;
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s %s", isCompleted ? "☑" : "☐", title);
    }
}
