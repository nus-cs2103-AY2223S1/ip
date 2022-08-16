import java.util.ArrayList;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
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
        return "[T]" + super.toString();
    }
}
