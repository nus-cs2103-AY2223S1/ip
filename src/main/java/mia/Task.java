package mia;

abstract public class Task {
    private String title;
    private boolean isCompleted = false;

    Task(String title) {
        this(title, false);
    }

    Task(String title, boolean isCompleted) {
        this.title = title;
        this.isCompleted = isCompleted;
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
     * @return {@code true} if the status is modified, {@code false} otherwise
     */
    public boolean setCompleted(boolean completed) {
        if (completed == this.isCompleted) {
            return false;
        }
        this.isCompleted = completed;
        return true;
    }

    public String toSaveFormat() {
        return String.format("%s;;%s;;", isCompleted ? 1 : 0, title);
    }

    @Override
    public String toString() {
        return String.format("%s %s", isCompleted ? "☑" : "☐", title);
    }
}
