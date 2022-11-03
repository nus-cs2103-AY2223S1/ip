package jude;

import jude.task.Task;
import jude.task.Todo;

/**
 * Test class for TaskList.
 */
class TaskListStub extends TaskList {

    // @inheritDoc adapted from https://github.com/nus-cs2103-AY2223S1/forum/issues/44
    /**
     * This is a mock method which does nothing. <br>
     * In the parent method, the following is done: <br>
     * {@inheritDoc}
     */
    @Override
    public void add(Task task) {}

    /**
     * This is a mock method which does nothing. <br>
     * In the parent method, the following is done: <br>
     * {@inheritDoc}
     */
    @Override
    public void delete(int index) {}

    /**
     * This is a mock method which returns a dummy completed todo task. <br>
     * In the parent method, the following is done: <br>
     * {@inheritDoc}
     */
    @Override
    public Task get(int index) {
        return new Todo("Todo Task 1", true);
    }
}
