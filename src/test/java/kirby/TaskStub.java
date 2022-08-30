package kirby;

import kirby.tasks.Task;

/**
 * TaskStub class creates a simplified version of a Task for testing.
 */
public class TaskStub extends Task {

    public TaskStub(String description) {
        super(description);
    }

    @Override
    public int[] getDate() {
        return new int[0];
    }
}
