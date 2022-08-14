public class Task {

    public boolean isDone;
    public String taskDescription;
    public int index;

    public Task(boolean isDone, String taskDescription, int index) {
        this.isDone = isDone;
        this.taskDescription = taskDescription;
        this.index = index;
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

}
