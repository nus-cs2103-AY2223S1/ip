public class Task {
    private String taskString;
    private boolean done;

    public Task(String taskString) {
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

    @Override
    public String toString() {
        if (done) {
            return "[" + this.getChar() + "][X] " + this.taskString;
        } else {
            return "[" + this.getChar() + "][ ] " + this.taskString;
        }
    }
}
