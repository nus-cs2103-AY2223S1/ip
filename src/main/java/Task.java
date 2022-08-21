public class Task {
    private String description;
    private boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    public String fileDescription() {
        if (isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    @Override
    public String toString() {
        String done;
        if (isDone) {
            done = "[X]";
        } else {
            done = "[ ]";
        }
        return done + " " + this.description;
    }

}
