package duke;

import java.util.ArrayList;

/**
 * Todo implements the type of Task that contains
 * the description of a certain Task to be completed.
 *
 * @author Alvin Jiang Min Jun
 * @version v0.1
 */
public class Todo extends Task {

    /**
     * Todo constructor that creates an instance of a Todo object.
     *
     * @param description The description for the task that needs to be completed.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * A method which adds a task to the given taskList input.
     *
     * @param taskList The taskList before a Todo Task is added.
     * @return String The String displayed when after the Todo Task is added.
     */
    @Override
    public String printAndStoreTask(ArrayList<Task> taskList) {
        taskList.add(this);
        return "Got it. I've added this task:\n"
                + "  " + this + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * The toString method for the Todo class.
     *
     * @return String The String format of the Todo Object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
