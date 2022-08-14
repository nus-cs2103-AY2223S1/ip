public class Todo extends Task{

    public Todo(String description) {
        super(description);
        numberOfTasks++;
    }

    @Override
    public String addedString() {
        return "Got it. I've added this task:\n " + this.toString() + "\nNow you have " + this.numberOfTasks + " tasks in the list.";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
