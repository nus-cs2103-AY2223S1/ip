public class Task {
    private final String task_description;
    private boolean done;
    private static final String done_msg = "Nice! I've marked this task as done:";
    private static final String undone_msg = "OK, I've marked this task as not done yet:";
    private static final String already = "This task is already marked as ";

    Task(String task_description) {
        this.task_description = task_description;
        this.done = false;
    }

    public void doing() {
        if (!this.done) {
            System.out.println(done_msg);
            this.done = true;
        } else {
            System.out.println(already + "done");
        }
        System.out.println(this.toString());
    }

    public void undo() {
        if (this.done) {
            System.out.println(undone_msg);
            this.done = false;
        } else {
            System.out.println(already + "not done");
        }
        System.out.println(this.toString());
    }

    public String description() {
        return this.task_description;
    }

    @Override
    public String toString() {
        String marker;
        if (this.done) {
            marker = "X";
        } else {
            marker = " ";
        }
        return "[" + marker + "] " + this.task_description;
    }
}
