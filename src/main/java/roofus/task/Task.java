package roofus.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;;
    }
    
    public void setDone() {
        this.isDone = true;
    }

    public void unmark() {
        isDone = false;
    }
    
    public String writeString() {
        return new String();
    }
    
    @Override
    public String toString() {
        if (isDone) {
            return "[X] "  + description;
        } else {
            return "[ ] " + description;
        }
    }
}
