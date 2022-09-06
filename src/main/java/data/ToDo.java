package data;

/**
 * Basic task with no date attached.
 */
public class ToDo extends Task {

    /**
     * Create a new ToDo item.
     *
     * @param title Title of task
     * @param done  If task is done.
     */
    public ToDo(String title, boolean done) {
        super(title, done);
        assert !title.isEmpty();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
