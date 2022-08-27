package kirby;

import kirby.tasks.Task;

public class TaskStub extends Task {

    public TaskStub(String description) {
        super(description);
    }

    @Override
    public int[] getDate() {
        return new int[0];
    }
}
