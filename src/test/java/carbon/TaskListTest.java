package carbon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import carbon.task.Task;

public class TaskListTest {
    private TaskList taskList = new TaskList();

    @Test
    public void listItems_noItems_returnsNoTasks() {
        String expectedOutput = "There are no tasks so far.";
        String actualOutput = this.taskList.listItems();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void addTask_todo_returnsAddedTask() {
        String input = "todo Complete Assignment";
        String expectedOutput = "I have added: \n"
                + "    \u001B[35m(TODO)\u001B[0m [ ] Complete Assignment !\n\n"
                + "    We've got 1 task so far.";
        String actualOutput = this.taskList.addTask(input, Task.Type.TODO);
        assertEquals(expectedOutput, actualOutput);
    }
}
