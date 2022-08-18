public class ToDo extends Task{
    public ToDo(String taskName) {
        super(taskName);
    }
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.taskName ;
    }
}

