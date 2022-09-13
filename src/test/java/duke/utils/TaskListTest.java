package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.tasks.Event;
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
        String keyword = "nothing much";
        taskList.addTask(vanillaTask);
        TaskList vanillaResult = taskList.findMatchingTasks(keyword);
        assertEquals(
                vanillaTask.getDescription(),
                vanillaResult.getTask(0).getDescription()
        );
    }

    @Test
    public void findInBetweenTaskTest() {
        TaskList taskList = new TaskList();
        Task inBetweenTask = new TaskStub("searchinbetweenwords");
        String keyword = "inbetween";
        taskList.addTask(inBetweenTask);
        TaskList inBetweenResult = taskList.findMatchingTasks(keyword);
        assertEquals(
                inBetweenTask.getDescription(),
                inBetweenResult.getTask(0).getDescription()
        );
    }

    @Test
    public void findSingleWordTaskTest() {
        TaskList taskList = new TaskList();
        Task singleWordSearchTask = new TaskStub("single word");
        String keyword = "single";
        taskList.addTask(singleWordSearchTask);
        TaskList singleWordResult = taskList.findMatchingTasks(keyword);
        assertEquals(
                singleWordSearchTask.getDescription(),
                singleWordResult.getTask(0).getDescription()
        );
    }

    @Test
    public void findMultipleTaskTest() {
        TaskList taskList = new TaskList();
        Task pairedOneTask = new TaskStub("pair one");
        Task pairedTwoTask = new TaskStub("pair two");
        String keyword = "pair";
        taskList.addTask(pairedOneTask);
        taskList.addTask(pairedTwoTask);
        TaskList pairedResult = taskList.findMatchingTasks(keyword);
        assertEquals(
                pairedOneTask.getDescription(),
                pairedResult.getTask(0).getDescription()
        );
        assertEquals(
                pairedTwoTask.getDescription(),
                pairedResult.getTask(1).getDescription()
        );
    }

    @Test
    public void findTaskLargeTaskListTest() {
        TaskList taskList = new TaskList();
        String keyword = "single";
        Task singleTask = new TaskStub("single");
        for (int i = 0; i < 20; i++) {
            if (i == 13) {
                taskList.addTask(singleTask);
            } else {
                taskList.addTask(new TaskStub("taskstub" + i));
            }
        }
        TaskList result = taskList.findMatchingTasks(keyword);
        assertEquals(
                singleTask.getDescription(),
                result.getTask(0).getDescription()
        );
    }

    @Test
    public void findDatedTaskTest() {
        Task event = new Event("test", false, LocalDateTime.parse("2022-09-07T04:20"));
        TaskList taskList = new TaskList();
        taskList.addTask(event);
        LocalDateTime eventBefore = LocalDateTime.parse("2022-09-07T04:19");
        LocalDateTime eventAfter = LocalDateTime.parse("2022-09-07T04:21");
        Task sameEvent = taskList.findDatedTasks(eventBefore, eventAfter).getTask(0);
        assertEquals(event, sameEvent);

        // reversedTaskList should be empty
        TaskList reversedTaskList = taskList.findDatedTasks(eventAfter, eventBefore);
        assertEquals(0, reversedTaskList.size());
    }
}
