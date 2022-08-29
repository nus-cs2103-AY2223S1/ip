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

    public boolean matches(String query) {
        final String[] queryWords = query.toLowerCase().split("\\s");
        for (String word : queryWords) {
            if (!title.toLowerCase().contains(word)) {
                return false;
            }
        }
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
