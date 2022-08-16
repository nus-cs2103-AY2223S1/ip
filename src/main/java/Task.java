public class Task {
    private String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }

    public String getStatus() {
        return String.format("[%c] %s", (done==true? 'X' : ' '), this.name);
    }

}
