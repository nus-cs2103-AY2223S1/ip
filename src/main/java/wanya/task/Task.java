package wanya.task;

public abstract class Task {
    private boolean hasCompleted = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public Task(String taskName, boolean hasCompleted) {
        this.taskName = taskName;
        this.hasCompleted = hasCompleted;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean hasCompleted() {
        return hasCompleted;
    }

    public void setComplete() {
        if (this.hasCompleted) {
            System.out.println("Wanya is confused errrrr... You have already marked this task as done!\n");
        }
        this.hasCompleted = true;
        System.out.println("Hehe well done! One task down, one step closer to play time!\n" +
                "This task has been completed:");
        System.out.println(this + "\n");
    }

    public void setIncomplete() {
        if (!this.hasCompleted) {
            System.out.println("Wanya is confused errrrr... This task is not done yet!\n");
        }
        this.hasCompleted = false;
        System.out.println("Oh nooo!!! Gotta buck up and finish up your tasks before you can play games.\n" +
                "This task has not been completed:");
        System.out.println(this + "\n");
    }

    public String toStorageString() {
        String status = hasCompleted ? "1" : "0";
        return status + "|" + taskName;
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
