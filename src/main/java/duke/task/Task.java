package duke.task;

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

    public Boolean isContainsKeyword(String keyword) {
        return this.description.toLowerCase().contains(keyword);
    }

    public String stringFormatting() {
        String marker = getStatus().equals("X") ? "T" : "F";
        return " # " + marker + " # " + this.description;
    }
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.description;
    }
}
