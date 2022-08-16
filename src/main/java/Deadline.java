import java.util.ArrayList;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public ArrayList<Task> printAndStoreTask(ArrayList<Task> taskList) {
        taskList.add(this);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
        return taskList;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

