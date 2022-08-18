public class Task {
    protected String description;
    protected Boolean isDone = false;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getTask() {
        return description;
    }
    public String getIsDone(){
        return (isDone ? "X" : " ");
    }
    public void setIsDone(Boolean done){
        isDone = done;
    }

}
