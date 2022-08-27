package utils;

import tasks.Deadline;
import tasks.Task;
import tasks.Todo;
import tasks.Event;
import tasks.ToDoList;
import utils.Storage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadTest1() {
        Storage storage = new Storage("./../../data/data.txt");
        ArrayList<Task> expected = storage.load();

        ArrayList<Task> actual = new ArrayList<>();
        actual.add(new Deadline("task 3", "2022-12-12 2222"));

        assertEquals(expected.toString(), actual.toString());
    }
}
