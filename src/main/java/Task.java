public class Task {

    private String desc;
    private boolean isDone;

    //constructor for Task
    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    public Task() throws DokeException {
        throw new DokeException();
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
    public void markDone() throws DokeException {
        if (isDone) {
            throw new DokeException();
        }
        isDone = true;
    }

    //a method to mark a task not done
    public void markNotDone() throws DokeException{
        if (!isDone) {
            throw new DokeException();
        }
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]   " + desc;
    }

}
