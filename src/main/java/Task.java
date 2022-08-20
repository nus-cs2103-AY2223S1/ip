public class Task {
    private String name;
    private boolean finished = false;

    public Task(String taskname) {
        this.name = taskname;
    }

    public void markAsDone() {
        finished = true;
    }

    @Override
    public String toString() {
        if (finished) {
            return "[/] " + this.name;
        } else {
            return "[X] " + this.name;
        }
    }
}
