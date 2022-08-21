public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void mark() {
        isDone = true;;
    }
    
    void setDone() {
        this.isDone = true;
    }

    void unmark() {
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
