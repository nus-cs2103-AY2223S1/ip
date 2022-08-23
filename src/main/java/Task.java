//@@author chengda300
//Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
// with minor modifications
public class Task {
    private boolean isDone;
    private String description;

    public Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void markTask() {
        this.isDone = true;
        System.out.println("Successfully marked this task as done: " + this);
    }

    public void unmarkTask() {
        this.isDone = false;
        System.out.println("Successfully marked this task as not done: " + this);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
//@@author