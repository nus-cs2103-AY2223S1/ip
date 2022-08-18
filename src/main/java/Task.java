public class Task {
    private boolean done;
    private String name;

    public Task(String name) {
       this.done = false;
       this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        if(done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
