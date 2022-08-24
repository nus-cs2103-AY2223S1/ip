package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        isDone = true;
    }

    public void unMark() {
        isDone = false;
    }

    /**
     * Checks if description of Task contains given keyword.
     * @param keyword Keyword to check.
     * @return true if the task contains keyword, otherwise false.
     */
    public boolean hasKeyword(String keyword) {
        String[] splitDescription = description.split(" ");
        for (int i = 0; i < splitDescription.length; i++) {
            if (splitDescription[i].toUpperCase().contains(keyword.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public String statusIcon() {
        return isDone ? "1" : "0";
    }

    public String storedString() {
        return statusIcon() + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
