package jarvis.task;

/**
 * A class that represent a todo, doesn't have time information
 */
public class Todo extends Task {

    /**
     * Construct a todo from users' input
     * @param input Description of the todo
     * @param isDone Whether the todo is done
     */
    public Todo(String input, boolean isDone) {
        super(isDone);
        this.description = input;
    }

    @Override
    public TaskType getTaskType() {
        return TaskType.ToDo;
    }

    /**
     * For storing todo to data file
     * @return The string representation of this todo in data file
     */
    @Override
    public String toDataForm() {
        String done = this.isDone ? "1" : "0";
        return "T|" + done + "|" + this.description + "\n";
    }

    @Override
    public String toString() {
        String head = "[T]" + "[" + this.getStatusIcon() + "] ";
        return head + this.description;
    }
}
