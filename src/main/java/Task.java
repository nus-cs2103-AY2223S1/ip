public class Task {
    private String description;
    private String addOn = "nya!";
    private Boolean completed;

    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public String describe() {
        return this.description + " " + addOn;
    }
    public Boolean status() {
        return this.completed;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public void markAsNotDone() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
