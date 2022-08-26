public class ToDo extends Task {

    public ToDo(String description) {
        super(description.trim());
        Task.taskCount++;
    }

    @Override
    public String toString() {
        return String.format("[T] [%s] %s", this.getStatusIcon(), this.description);
    }

}


