public class Task {
    private final String TASK;
    private Boolean completionStatus;
    private String completionIcon;
    Task(String task) {
        this.TASK = task;
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    void setCompletionStatus(boolean set) {
        completionStatus = set;
        if (completionStatus) {
            completionIcon = "[X]";
        } else {
            completionIcon = "[ ]";
        }
    }

    @Override
    public String toString() {
        return completionIcon + " - " + TASK;
    }
}
