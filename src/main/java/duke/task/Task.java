package duke.task;

public class Task {
    public String name;
    public boolean isDone;

    public Task() {
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public boolean needToRemind() {
        return false;
    }

    public String getOutput() {
        return String.format("O | %d | %s ", isDone ? 1 : 0, name );
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[] " + this.name;
        }
    }

}

