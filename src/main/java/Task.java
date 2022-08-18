public class Task {
    final private String taskName;
    private boolean done;

    public Task(String taskName) {
        this.taskName = taskName;
        this.done = false;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void markDone() {
        this.done = true;
        System.out.println("Nice! I have marked (" + this.taskName + ") as done!");
    }

    public void markUndone() {
        this.done = false;
        System.out.println("Nice! I have marked (" + this.taskName + ") as undone!");
    }

    @Override
    public String toString() {
        String checkbox = this.done ? "[✔] " : "[ ] ";
        return checkbox + this.taskName;
    }
}