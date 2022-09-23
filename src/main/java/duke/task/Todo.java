package duke.task;

import duke.util.Parser;

/**
 * Represents Todo tasks.
 */
public class Todo extends Task {
    public static final String SYMBOL = "T";

    protected Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }

    /**
     * Returns the formatted string representation of the object.
     * @return The formatted string representation of the object.
     */
    @Override
    public String toFormattedString() {
        return Parser.combineAttributes(SYMBOL,
                Integer.toString(Parser.convertBoolToInt(this.getIsDone())),
                this.getName());
    }

    /**
     * Returns an identical Todo.
     * @return An identical Todo.
     */
    @Override
    public Todo clone() {
        Todo result = new Todo(String.valueOf(this.getName()));
        if (this.getIsDone()) {
            result.markAsDone();
        }
        return result;
    }

    /**
     * Returns whether the given Task is same as this one.
     * @param task The given Task
     * @return The boolean whether the given Task is same as this one.
     */
    @Override
    public boolean isSameTask(Task task) {
        if (task instanceof Todo) {
            return this.getName().equals(task.getName());
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Todo) {
            Todo t = (Todo) obj;
            if (t == null) {
                return false;
            }
            return this.getName().equals(t.getName()) && this.getIsDone() == t.getIsDone();
        }
        return false;
    }
}
