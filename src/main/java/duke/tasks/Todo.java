package duke.tasks;

public class Todo extends Task {

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
