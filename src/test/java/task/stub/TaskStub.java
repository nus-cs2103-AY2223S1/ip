package task.stub;

import task.Task;

/**
 * A stub for the Task class
 *
 * @author Bryan Lim Jing Xiang
 */
public class TaskStub extends Task {
    public TaskStub() {
        super("Testing!");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String encode() {
        return this.toString();
    }

    /**
     * {@inheritDoc}
     * Compares two stubs for equality for testing convenience
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o instanceof TaskStub) {
            return this.getIsMarked() == ((TaskStub) o).getIsMarked();
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Testing!";
    }
}
