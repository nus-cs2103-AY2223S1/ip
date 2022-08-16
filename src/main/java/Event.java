import java.util.ArrayList;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
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
        return "[E]" + super.toString() + " (by: " + at + ")";
    }
}