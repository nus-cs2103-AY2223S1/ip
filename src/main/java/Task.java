public class Task {
    private boolean completed = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean hasCompleted() {
        return completed;
    }

    public void completedTask() {
        this.completed = true;
        System.out.println("Hehe well done! One task down, one step closer to play time! \nThis task has been completed:");
        System.out.println(this);
    }

    public void uncompletedTask() {
        this.completed = false;
        System.out.println("Oh nooo!!! Gotta buck up and finish up your tasks before you can play games. \nThis task has not been completed:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        String checkbox;
        if (this.hasCompleted()) {
            checkbox = "[X] ";
        } else {
            checkbox = "[ ] ";
        }
        return checkbox + this.taskName;
    }

}
