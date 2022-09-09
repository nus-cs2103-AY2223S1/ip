package duke.task;

public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? "[X]" : "[ ]";
        return statusIcon + " " + description;
    }
    
    public boolean containsKeyword(String keyword) {
        return description.contains(keyword);
    }
    
    @Override
    public int compareTo(Task task) {
        return this instanceof DeadlineTask
                ? this.compareTo(task)
                : task instanceof DeadlineTask
                ? 1
                : 0;
    }
}
