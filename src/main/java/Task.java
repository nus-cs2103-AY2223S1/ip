public class Task {
    private final String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
    }

    private String getStatus() {
        if (isCompleted) {
            return "X";
        }
        return "";
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}
