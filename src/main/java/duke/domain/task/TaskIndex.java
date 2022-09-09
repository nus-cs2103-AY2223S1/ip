package duke.domain.task;

import duke.exceptions.TaskIndexOutOfBoundsException;

public class TaskIndex {
    private final int zeroBasedTaskIndex;

    private TaskIndex(int zeroBasedTaskIndex) {
        if (zeroBasedTaskIndex < 0) {
            throw new TaskIndexOutOfBoundsException();
        }
        this.zeroBasedTaskIndex = zeroBasedTaskIndex;
    }

    public int getZeroBased() {
        return zeroBasedTaskIndex;
    }

    public int getOneBased() {
        return zeroBasedTaskIndex + 1;
    }

    public static TaskIndex fromZeroBased(int zeroBasedTaskIndex) {
        return new TaskIndex(zeroBasedTaskIndex);
    }

    public static TaskIndex fromOneBased(int oneBasedTaskIndex) {
        return new TaskIndex(oneBasedTaskIndex - 1);
    }
}
