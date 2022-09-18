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

    /**
     * Compare this todo with other tasks
     * Todos have lower priority than deadlines and events(1)
     * Compare among events by the alphabetical order of
     * their description
     * @param task2 the other task to be compared with
     * @return The priority -1, 0 or 1 as specified above
     */
    @Override
    public int compareTo(Task task2) {
        if (task2 instanceof Event || task2 instanceof Deadline) {
            return 1;
        }
        return this.description.compareTo(task2.description);
    }
}
