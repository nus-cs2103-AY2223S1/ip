public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void setDone(){
        this.isDone = true;
    }

    public void setUndone(){
        this.isDone = false;
    }

    @Override
    public String toString(){
        return isDone ? "[X] " + this.description : "[ ] " + this.description;
    }
}
