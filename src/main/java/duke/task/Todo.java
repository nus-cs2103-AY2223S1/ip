package duke.task;

import duke.Parser;

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

    @Override
    public String toFormattedString() {
        return Parser.combineAttributes(SYMBOL,
                Integer.toString(Parser.convertBoolToInt(this.getIsDone())),
                this.getName());
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
