public class Task {
    private String description;
    private boolean isDone;
    public Task(String description){
        this.description=description;
        this.isDone=false;
    }
    //return the status of this task
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    //mark this task as Done without reply
    public void taskDone(){
        this.isDone=true;

    }
    //undone this task and print reply
    public void taskUndone(){
        this.isDone=false;
    }
    //print the representation of this task containing status and description
    public String printTask(){
        return ("["+this.getStatusIcon()+"]"+" "+this.description);
    }
}
