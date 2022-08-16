public class Task {
    protected String description;
    protected boolean isDone;

    public void addTaskMessage(){
        System.out.println("Gotcha, I've added this task: ");
        System.out.println(this.toString());
    }
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void setDone(){
        this.isDone = true;
    }

    public void setUndone(){
        this.isDone = false;
    }

    @Override
    public String toString(){
        return this.isDone ? "[X] " + this.description : "[ ] " + this.description;
    }
}
