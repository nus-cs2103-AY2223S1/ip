package duke.domain.task;

import duke.exceptions.TaskIndexOutOfBoundsException;

/**
 * TaskIndex Class
 */
public class TaskIndex {
    private final int zeroBasedTaskIndex;

    /**
     * TaskIndex constructor method
     *
     */
    private TaskIndex(int zeroBasedTaskIndex) {
        if (zeroBasedTaskIndex < 0) {
            throw new TaskIndexOutOfBoundsException();
        }
        this.zeroBasedTaskIndex = zeroBasedTaskIndex;
    }

    /**
     * Return index with the count start at 0
     *
     * @return index starting from 0
     */
    public int getZeroBased() {
        return zeroBasedTaskIndex;
    }

    /**
     * Return index with the count start at 1
     *
     * @return index starting from 1
     */
    public int getOneBased() {
        return zeroBasedTaskIndex + 1;
    }

    /**
     * Return new TaskIndex object based on zero based indexing from given index
     *
     * @return TaskIndex object
     */
    public static TaskIndex fromZeroBased(int zeroBasedTaskIndex) {
        return new TaskIndex(zeroBasedTaskIndex);
    }

    /**
     * Return new TaskIndex object based on one based indexing from given index
     *
     * @return TaskIndex object
     */
    public static TaskIndex fromOneBased(int oneBasedTaskIndex) {
        return new TaskIndex(oneBasedTaskIndex - 1);
    }
}
