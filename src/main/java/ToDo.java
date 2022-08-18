// ToDo class a child class of the task and has similar functionality.

public class ToDo extends Task {

    // Constructor
    public ToDo(String description) {
        super(description);
    }

    // toString method to change the display for different types of tasks on the console
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}