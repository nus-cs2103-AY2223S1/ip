package Duke.Tasks;

public abstract class Task {
    protected String discription;
    protected boolean isDone;

    public Task(String discription) {
        this(discription, false);
    }
    public Task(String discription, boolean isDone){
        this.discription = discription;
        this.isDone = isDone;
    }


    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "True" : "False");
    }

    public boolean getStatus() {
        return this.isDone;
    }


    @Override
    public String toString(){
        return getStatusIcon() + " | " + this.discription;
    }
}
