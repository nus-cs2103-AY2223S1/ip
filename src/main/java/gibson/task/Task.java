package gibson.task;

public class Task {
    private String taskString;
    private boolean done;

    public Task(String taskString) {
        if (taskString.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be blank.");
        }
        this.taskString = taskString;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public char getChar() {
        return 'T';
    }

    public boolean taskContains(String s) {
        return taskString.contains(s);
    }

    @Override
    public String toString() {
        if (done) {
            return "[" + getChar() + "][X] " + taskString;
        } else {
            return "[" + getChar() + "][ ] " + taskString;
        }
    }
}
