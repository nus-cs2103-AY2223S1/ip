package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void size_emptyTaskList_returnsZero() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.size());
    }

    @Test
    void size_nonEmptyTaskList_returnsSize() {
        TaskList taskList = new TaskList(new TodoTask("Task 1"), new TodoTask("Task 2"));
        assertEquals(2, taskList.size());
    }

    @Test
    void get_indexInBound_returnsTask() {
        TaskList list = new TaskList(new TodoTask("Task 1"), new TodoTask("Task 2"), new TodoTask("Task 3"));
        assertEquals(new TodoTask("Task 1"), list.get(0));
        assertEquals(new TodoTask("Task 2"), list.get(1));
        assertEquals(new TodoTask("Task 3"), list.get(2));
    }

    @Test
    void remove_indexInBound_removesTask() {
        TaskList list = new TaskList(new TodoTask("Task 1"), new TodoTask("Task 2"), new TodoTask("Task 3"));
        list.remove(1);
        assertEquals(new TodoTask("Task 1"), list.get(0));
        assertEquals(new TodoTask("Task 3"), list.get(1));
    }

    @Test
    void add_newTask_addsTask() {
        TaskList list = new TaskList();
        list.add(new TodoTask("task1"));
        assertEquals(new TodoTask("task1"), list.get(0));
    }
}
