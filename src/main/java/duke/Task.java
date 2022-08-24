package duke;
public class Task {
    private String description;
    private boolean isDone;

    public void addTaskMessage(){
        System.out.println("Gotcha, I've added this task:");
        System.out.println(this.toString());
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void setDone(){
        this.isDone = true;
    }

    public void setUndone(){
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString(){
        return this.isDone ? "[X] " + this.description : "[ ] " + this.description;
    }
}
