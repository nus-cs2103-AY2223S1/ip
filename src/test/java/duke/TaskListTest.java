package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTask_addNewTaskViaTaskList_addNewTaskDirectlyToArrayList(){
        String description = "do homework";
        Task newTask = new ToDo(description);

        ArrayList<Task> arrayList_taskList = new ArrayList<Task>();
        arrayList_taskList.add(newTask);

        TaskList taskList = new TaskList();
        taskList.add(newTask);

        assertEquals(arrayList_taskList.toString(), taskList.toString());
    }
}
