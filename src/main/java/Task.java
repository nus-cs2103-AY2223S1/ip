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

    public void Toggle() {
        this.completed = !this.completed;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
