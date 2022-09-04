package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import duke.tasks.Task;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        Task task = new TaskStub("");
        TaskList taskList = new TaskList();
        taskList.addTask(task);
        assertEquals(taskList.getTask(0), task);
    }

    @Test
    public void deleteTaskTest() {
        IndexOutOfBoundsException thrown = assertThrows(IndexOutOfBoundsException.class, () -> {
            TaskList taskList = new TaskList();
            taskList.deleteTask(0);
        });
        assertEquals("Index 0 out of bounds for length 0", thrown.getMessage());
    }

    @Test
    public void findTaskTest() {
        TaskList taskList = new TaskList();

        Task vanillaTask = new TaskStub("nothing much");
        String vanillaKeyword = "nothing much";
        taskList.addTask(vanillaTask);
        TaskList vanillaResult = taskList.findMatchingTasks(vanillaKeyword);
        assertEquals(
                vanillaTask.getDescription(),
                vanillaResult.getTask(0).getDescription()
        );

        Task inBetweenTask = new TaskStub("searchinbetweenwords");
        String inBetweenKeyword = "inbetween";
        taskList.addTask(inBetweenTask);
        TaskList inBetweenResult = taskList.findMatchingTasks(inBetweenKeyword);
        assertEquals(
                inBetweenTask.getDescription(),
                inBetweenResult.getTask(0).getDescription()
        );

        Task singleWordSearchTask = new TaskStub("single word");
        String singleWordSearchKeyword = "single";
        taskList.addTask(singleWordSearchTask);
        TaskList singleWordResult = taskList.findMatchingTasks(singleWordSearchKeyword);
        assertEquals(
                singleWordSearchTask.getDescription(),
                singleWordResult.getTask(0).getDescription()
        );

        Task pairedOneTask = new TaskStub("pair one");
        Task pairedTwoTask = new TaskStub("pair two");
        String pairedKeyword = "pair";
        taskList.addTask(pairedOneTask);
        taskList.addTask(pairedTwoTask);
        TaskList pairedResult = taskList.findMatchingTasks(pairedKeyword);
        assertEquals(
                pairedOneTask.getDescription(),
                pairedResult.getTask(0).getDescription()
        );
        assertEquals(
                pairedTwoTask.getDescription(),
                pairedResult.getTask(1).getDescription()
        );
    }
}
