public class Task {
    protected String desc;
    protected boolean isDone;

    public Task(String desc) {
        this.desc = desc;
        isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void complete() {
        this.isDone = true;
        System.out.println("Nice I have marked the following task as done:");
        System.out.println(this);
    }

    public void undo() {
        this.isDone = false;
        System.out.println("Ok, I have marked this task as not done yet:");
        System.out.println(this);
    }

    public void add() {
        Tracker.list.add(this);
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        System.out.println("Now you have " + Tracker.list.size() + " tasks in the list");
    }


    @Override
    public String toString() {
        return "[" + getStatusIcon() +"] " + this.desc;
    }
}
