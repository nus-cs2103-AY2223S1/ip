package Duke;

public class Task {

    protected final String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }


    public void markAsNotDone() {
        this.isDone = false;
    }

    public String fileFormat() {
        if(isDone) {
            return "1|" + this.name;
        }
        return"0|" + this.name;
    }

    @Override
    public String toString() {
        if(isDone) {
            return "[X] " + this.name;
        }
        return"[ ] " + this.name;
    }
}
