package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;


class StorageTest {
    @Test
    void testSaveLoadTasks_empty() throws DukeException {
        TaskList list = new TaskList();
        String testFile = "test.txt";
        Storage storage = new Storage(testFile);
        storage.saveTasks(list);
        storage.loadTasks(testFile);
    }

    @Test
    void testSaveLoadTasks_nonEmpty() throws DukeException {
        TaskList list = new TaskList();
        String testFile = "test.txt";
        ToDo t1 = new ToDo("test1");
        ToDo t2 = new ToDo("test2");
        t2.setDone(true);
        Deadline d1 = new Deadline("test3", LocalDateTime.MIN);
        Deadline d2 = new Deadline("test4", LocalDateTime.MIN);
        d2.setDone(true);
        Event e1 = new Event("test5", "details");
        Event e2 = new Event("test6", "details");
        e2.setDone(true);
        list.addAll(List.of(new Task[]{t1, t2, d1, d2, e1, e2}));
        Storage storage = new Storage(testFile);
        storage.saveTasks(list);
        Optional<TaskList> newList = storage.loadTasks(testFile);
        Assertions.assertTrue(newList.isPresent());
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), newList.get().get(i));
        }
    }
}
