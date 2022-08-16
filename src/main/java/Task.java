public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
        String output = String.format("Nice! I've marked this dask as done: \n [%s] %s", this.getStatusIcon(), this.description);
        System.out.println(output);
    }

    public void unmark() {
        this.isDone = false;
        String output = String.format("Nice! I've marked this dask as not done yet: \n [%s] %s", this.getStatusIcon(), this.description);
        System.out.println(output);
    }
}