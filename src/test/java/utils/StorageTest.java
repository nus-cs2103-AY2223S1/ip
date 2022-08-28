package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import entities.Deadline;
import entities.Event;
import entities.Task;
import entities.Todo;

public class StorageTest {
    @Test
    public void testLoad() {
        Storage s = new Storage("data/test.txt");
        List<Task> actual = s.load();
        List<Task> expected = new ArrayList<>();
        Task t = new Todo("sth");
        Task e = new Event("visit ah ma", "bedok");
        Task d = new Deadline("catch up on sleep", "2022-08-23 2200");
        e.markAsDone();
        expected.add(t);
        expected.add(e);
        expected.add(d);
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(actual.get(i).toString(), expected.get(i).toString());
        }
    }
}
