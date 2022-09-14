package pluto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pluto.task.Task;
import pluto.task.Todo;



public class StorageTest {
    @Test
    public void load_convertFiletoList_success() throws IOException, PlutoException {
        try {
            Storage storage = new Storage("PlutoData.txt");
            TaskList tasks = new TaskList();
            Ui ui = new Ui();
            tasks.addTask(new Todo("sleep"));
            tasks.addTask(new Todo("study"));
            tasks.addTask(new Todo("eat"));
            storage.rewriteFile(tasks);
            ArrayList<Task> arrTasks = storage.load();
            assertEquals(tasks.nTasks(), arrTasks.size());
            for (int i = 0; i < arrTasks.size(); i++) {
                assertEquals(arrTasks.get(i), tasks.getTask(i));
            }
        } catch (PlutoException e) {
            assertEquals("OOPS!!! Couldn't find/create data file.", e.getMessage());
        }
    }
}
