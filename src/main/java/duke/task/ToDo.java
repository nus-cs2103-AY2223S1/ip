package duke.task;

/**
 * Class extending Task representing a ToDo task.
 */
public class ToDo extends Task {
    public ToDo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return String.format("[T]%s %s", super.toString(), this.tag);
    }

    /**
     * Produces a string representation of this ToDo to be stored in a text file.
     * @return string representation of this ToDo.
     */
    @Override
    public String toFileData() {
        return String.format("T | %d | %s | %s", this.isDone ? 1 : 0, this.content, this.tag);
    }
}
