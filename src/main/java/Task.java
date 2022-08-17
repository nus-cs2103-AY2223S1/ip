public class Task {
    private String task;
    private boolean done;
    Task(String task)
    {
        this.task = task;
        this.done = false;
    }

    @Override
    public String toString()
    {
        return getStatusIcon() + " " + task;
    }

    public String getTask()
    {
        return this.task;
    }
    public String getStatusIcon()
    {
        return this.done ? "[X]" : "[]";
    }
    public void markTaskAsDone()
    {
        done = true;
    }
    public void unMarkTaskAsDone()
    {
        done = false;
    }
}
