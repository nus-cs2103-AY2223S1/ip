package duke;

/**
 * Encapsulates the basic Task, which only has a name and status.
 */
class Todo extends Task {
    /**
     * Creates a new Todo task.
     * @param name The task to be completed.
     */
    Todo (String name) {
        super(name);
    }

    /**
     * Creates a new Todo task.
     * @param name The task to be completed.
     * @param done The status of the task.
     */
    Todo (String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        String out = "[T][";
        if (super.getStatus()) {
            out += "X";
        } else {
            out += " ";
        }
        out += "] " + super.toString();
        return out;
    }
}
