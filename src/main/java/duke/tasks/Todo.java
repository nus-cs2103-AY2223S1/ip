package duke.tasks;

/**
 * Todo implements methods for Todo objects.
 *
 * @author Isaac Li Haoyang
 * @version v0.1
 */
public class Todo extends Task {

    /**
     * Creates a new Todo object.
     *
     * @param task description of the deadline task
     */
    public Todo(String task) {
        super(task);
    }

    /**
     * Fetches the type of task for identification during encoding and decoding for storage.
     *
     * @return a Character 'T' representing Todo
     */
    @Override
    public char getTaskType() {
        return 'T';
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
