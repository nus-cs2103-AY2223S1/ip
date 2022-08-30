package duke.chatbot.data.task;

/**
 * A type of task that only has a description.
 * @author Jordan Quah Shao Xuan
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String encode() {
        return "T" + super.encode();
    }
}
