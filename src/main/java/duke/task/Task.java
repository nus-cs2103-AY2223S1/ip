package duke.task;

public class Task {
    protected String name;
    protected boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void completed() {
        this.done = true;
    }

    public void uncompleted() {
        this.done = false;
    }

    public String getStatusIcon() {
        return (this.done) ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.name);
    }

    public String savedString() {
        return String.format("%s,%s", (this.done) ? "Y" : "N" , this.name);
    }


}
