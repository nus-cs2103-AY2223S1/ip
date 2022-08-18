public class Task {
    private final String val;
    private final boolean done;

    public Task(String input) {
        this.val = input;
        done = false;
    }

    private Task(String input, boolean done) {
        this.val = input;
        this.done = done;
    }

    public Task markDone() {
        return new Task(this.val, true);
    }

    public Task markUndone() {
        return new Task(this.val, false);
    }

    public boolean getDone() {
        return this.done;
    }

    public String getVal() {
        return this.val;
    }

}
