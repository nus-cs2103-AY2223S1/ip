public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String str;
        if (isDone){
            str = String.format("%s [X]", this.description);
        } else {
            str = String.format("%s [ ]", this.description);
        }
        return str;
    }
}
