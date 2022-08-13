/**
 * Encapsulate a Task that user adds to the list.
 *
 * @author: Jonas Png
 */
public abstract class Task {

    private  String description;

    private boolean isDone;

    /**
     * Class constructor for ListItem.
     *
     * @param description name of item.
     * @throws DukeException If user did not give description of task.
     */
    public Task(String description) throws DukeException{
        if (description.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Method to get the name of the item.
     *
     * @return  name of item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark item as Done.
     *
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
    }

    /**
     * Mark item as not Done.
     *
     */
    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this);
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }

}
