public abstract class Task {
    protected String discription;
    protected boolean isDone;

    public Task(String discription) {
        this.discription = discription;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString(){
        return "[" + getStatusIcon() + "] " + this.discription;
    }
}
