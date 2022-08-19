public abstract class Task {
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
        if (this.completed) {
            System.out.println("Wanya is confused errrrr... You have already marked this task as done!\n");
        }
        this.completed = true;
        System.out.println("Hehe well done! One task down, one step closer to play time!\n" +
                "This task has been completed:");
        System.out.println(this + "\n");
    }

    public void uncompletedTask() {
        if (!this.completed) {
            System.out.println("Wanya is confused errrrr... This task is not done yet!\n");
        }
        this.completed = false;
        System.out.println("Oh nooo!!! Gotta buck up and finish up your tasks before you can play games.\n" +
                "This task has not been completed:");
        System.out.println(this+ "\n");
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
