public class Task {
    private String desc;
    private boolean isDone;

    //constructor for Task
    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    //a method to get Status Icon of the task
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    //a method to get the description of the task
    public String getDesc() {
        return desc;
    }

    //a method to mark a task done
    public void markDone() throws Exception {
        if (isDone) {
            throw new Exception();
        }
        isDone = true;
    }

    //a method to mark a task not done
    public void markNotDone() throws Exception{
        if (!isDone) {
            throw new Exception();
        }
        isDone = false;
    }

}
