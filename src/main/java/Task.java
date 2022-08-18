public class Task {
    private final String desc;
    private final int id;
    private final char completed;
    private final char taskType;

    public Task(String desc, int id, char taskType) {
        this.id = id;
        this.desc = desc;
        this.completed = ' ';
        this.taskType = taskType;
    }

    private Task(String desc, int id, char completed, char taskType) {
        this.desc = desc;
        this.id = id;
        this.completed = completed;
        this.taskType = taskType;
    }

    protected Task updateId(int id) {
        return new Task(this.desc, id, this.completed, this.taskType);
    }

    protected Task performTask() {
        System.out.println("Nice! I've marked this task as done");
        Task performedTask = new Task(this.desc, this.id,'X', this.taskType);
        System.out.printf("%s\n", performedTask.taskStatus());
        return performedTask;
    }

    protected Task undoTask() {
        System.out.println("OK, I've marked this task as not done yet");
        Task undidTask = new Task(this.desc, this.id, this.taskType);
        System.out.printf("%s\n", undidTask.taskStatus());
        return undidTask;
    }
    protected String taskStatus() {
        return String.format("\t[%c][%c] %s", this.taskType, this.completed, this.desc);
    }

    public String toString() {
        return String.format("\t%d[%c][%c] %s", this.id, this.taskType, this.completed, this.desc);
    }
}
