public class Task {
    private String desc;
    private int index;
    private char completed;

    public Task(String desc, int index) {
        this.index = index;
        this.desc = desc;
        this.completed = ' ';
    }

    private Task(String desc, int index, char completed) {
        this.desc = desc;
        this.index = index;
        this.completed = completed;
    }

    protected Task performTask() {
        System.out.println("Nice! I've marked this task as done");
        Task performedTask = new Task(this.desc, this.index,'X');
        System.out.printf("\t%s\n", performedTask);
        return performedTask;
    }

    protected Task undoTask() {
        System.out.println("OK, I've marked this task as not done yet");
        Task undidTask = new Task(this.desc, this.index);
        System.out.printf("\t%s\n", undidTask);
        return undidTask;
    }

    public String toString() {
        return String.format("\t%d[%c] %s", this.index, this.completed, this.desc);
    }
}
