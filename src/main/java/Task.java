public class Task {

    private String item;
    private boolean completed;

    public Task(String item) {
        this.item = item;
        this.completed = false;
    }

    // this is implemented as a toggle; could be problematic in future
    public boolean completeToggle() {
        this.completed = !this.completed;
        return this.completed;
    }

    @Override
    public String toString() {
        if (completed) {
            return "[ ] " + item;
        }

        return "[X] " + item;
    }

}
