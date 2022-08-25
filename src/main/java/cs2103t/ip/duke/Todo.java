package cs2103t.ip.duke;

public class Todo extends Task {

    protected String by;

    public Todo(String description) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a message to indicate that a task has been successfully added
     * that is shown to user when a task of type to do is added to the list.
     * @return A message shown to user upon addition of task.
     */
    @Override
    public String addString(int i) {
        String line = "_______________________________\n";
        String gotIt = "Got it. I've added this task: \n";
        String task = this.toString() + "\n";
        String now = String.format("Now you have %d tasks in the list \n", i);
        return line + gotIt + task + now + line;
    }

    /**
     * Returns a string representation of the task of type to do.
     * @return String representation of the task of type to do.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of the task to be saved into the file.
     * @return String representation of the task.
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return "T" + "X " + this.description + "\n";
        } else {
            return "T  " + this.description + "\n";
        }
    }
}
