public class Task {
    protected String description;
    protected boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public String getDescription() {
        return description; // mark done task with X
    }

    public String getStatus() {
        return (status ? "X" : " "); // mark done task with X
    }

    public void changeStatus(boolean status) {
        this.status = status;
    }
}

