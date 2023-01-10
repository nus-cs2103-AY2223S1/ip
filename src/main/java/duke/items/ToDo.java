package duke.items;

/**
 * Object representing a ToDo Item.
 */
public class ToDo extends Item {

    /**
     * Create an incomplete ToDo Item.
     * @param name Item description.
     */
    public ToDo(String name) {
        super(name, ItemTypes.TODO, null);
    }

    /**
     * Create a ToDo Item and set the completion status of the task.
     * @param name Item description.
     * @param isDone Completion status of the task.
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone, ItemTypes.TODO, null);
    }

    @Override
    public String toString() {
        return super.getItemType() + super.toString();
    }
}
