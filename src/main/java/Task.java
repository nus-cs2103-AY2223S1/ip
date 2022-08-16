public class Task {
    private Boolean isDone;
    private int id;
    private static int numOfTasks = 0;
    private String description;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        Task.numOfTasks += 1;
        this.id = Task.numOfTasks;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public int getId() {
        return this.id;
    }
}
