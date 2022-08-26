package duke.task;

abstract public class Task {
    protected String description;
    private boolean isDone;

    public Task(){
        this.description = null;
        this.isDone = false;
    };

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean isDone){
        this.description = null;
        this.isDone = isDone;
    };

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public abstract String processData();

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
