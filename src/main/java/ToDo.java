public class ToDo extends Task{
    public ToDo(String taskName, boolean isDone) {
        super(taskName,isDone);
    }
    @Override
    public String toString() {
        return "[T]" + "[" + this.getStatusIcon() + "] " + this.taskName ;
    }

    @Override
    public String toStore() {
        return "|T|" + "|" + this.getStatusIcon() + "| " + this.taskName ;
    }
}

