public class Task {
    private String name;
    private boolean isCompleted;
    private int taskNumber;

    // Constructor for each task
    public Task(String name, int taskNumber) {
        this.name = name;
        this.isCompleted = false;
        this.taskNumber = taskNumber;
    }

    // Returns a status icon if its completed/uncompleted
    public String getStatus() {
        return isCompleted ? "X" : " ";
    }

    // Returns the task number
    public int getTaskNumber() {
        return this.taskNumber;
    }

    // Marks the task to show completed
    public void mark() {
        this.isCompleted = true;
    }

    // Unmarks the task to show uncompleted
    public void unmark() {
        this.isCompleted = false;
    }

    // Overridden toString method
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatus(), name);
    }
}
