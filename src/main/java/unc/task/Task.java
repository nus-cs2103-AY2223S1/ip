package unc.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String toStorageString();
    public boolean hasKeyword(String keyword){
        String[] words = this.toString().split("\\s+");
        boolean isFound = false;
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }

}
