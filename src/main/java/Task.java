public class Task {
    private String task;
    private boolean done;

    public boolean done() {
        done = true;
        return true;
    }

    public boolean notDone(){
        done = false;
        return false;
    }

    @Override
    public String toString(){
        if (done) {
            return "[X] " + task;
        } else {
            return "[ ] " + task;
        }
    }

    public Task(String task) {
        this.task = task;
        this.done = false;
    }
}
