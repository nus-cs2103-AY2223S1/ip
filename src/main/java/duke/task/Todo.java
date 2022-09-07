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

    /**
     * Return the String representation of the Task.
     * @return The String representation of the Task.
     */
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
     * Returns boolean indicating whether this object
     * is equivalent to another object.
     *
     * @param obj The object to be checked.
     * @return The boolean whether the given object is equivalent to this object.
     */
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
            if (this.getName() == t.getName()) {
                return true;
            }
            if (this.getName() == null) {
                return false;
            }
            if (t.getName() == null) {
                return false;
            }
            return this.getName().equals(t.getName())
                    && this.getIsDone() == t.getIsDone();
        }
        return false;
    }
}
