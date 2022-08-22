package duke.task;

public abstract class Task {
    private final String description;
    private boolean isFinished;

    public Task(String description) {
        this.description = description;
        this.isFinished = false;
    }

    public void markAsFinished() {
        this.isFinished = true;
    }

    public void markAsNotFinished() {
        this.isFinished = false;
    }

    protected String getStatusIcon() {
        return this.isFinished ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public String toStorageRepresentation() {
        return this.getStatusIcon() + "|" + this.description;
    }

    /**
     * Returns true if the description of current Task matches with the keyword.
     *
     * @param keyWord the keyword string.
     * @return true if Task matches the keyword, false otherwise.
     */
    public boolean isContainKeyWord(String keyWord) {
        return this.description.contains(keyWord);
    }

}
