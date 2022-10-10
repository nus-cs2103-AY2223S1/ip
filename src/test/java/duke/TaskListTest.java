package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTaskTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Event("event1", LocalDate.parse("2022-04-24"), LocalDate.parse("2022-04-26")));
        taskList.addTask(new ToDo("todo1"));
        taskList.addTask(new Event("event2", LocalDate.parse("2022-04-20"), LocalDate.parse("2022-04-25")));
        taskList.addTask(new Deadline("ddl1", LocalDate.parse("2022-04-28")));
        taskList.addTask(new ToDo("todo2"));
        taskList.addTask(new ToDo("todo3"));
        taskList.addTask(new Deadline("ddl2", LocalDate.parse("2022-04-21")));
        assertEquals(7,taskList.getSize());
        assertEquals("event1 (at 2022-04-24 - 2022-04-26)",taskList.getTask(0).getDescription());
        assertEquals("ddl2 (by 2022-04-21)",taskList.getTask(6).getDescription());
        assertEquals("todo1", taskList.deleteTaskAtIndex(2).getDescription());
        assertEquals(6,taskList.getSize());
        taskList.markAsDone(2);
        taskList.markAsDone(3);
        assertEquals(true, taskList.getTask(1).getStatus());
        assertEquals(true, taskList.getTask(2).getStatus());
        taskList.markAsUndone(3);
        assertEquals(false, taskList.getTask(2).getStatus());
        taskList.markAsUndone(4);
        assertEquals(false, taskList.getTask(3).getStatus());
    }
}


