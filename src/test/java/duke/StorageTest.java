package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;



public class StorageTest {
    private TaskList taskList;
    private Storage storage;

    public StorageTest() {
        this.taskList = new TaskList();
        try {
            this.storage = new Storage();
        } catch (DukeException e) {
            fail(e);
        }
    }

    public void writeTestData() {
        try {
            FileWriter file = new FileWriter("data/duke.txt", false);
            file.write("E|1|test duke|2019-01-20\nD|0|test deadline|2019-10-15\nT|0|test test");
            file.close();
        } catch (IOException e) {
            fail(new DukeException("An error occurred when writing to file"));
        }
    }

    @Test
    public void loadDataTest() {
        writeTestData();
        try {
            storage.loadData(taskList);
            assertEquals(3, taskList.getSize());
            Task[] tasks = {
                new Event("test duke", LocalDate.parse("2019-01-20")),
                new Deadline("test deadline", LocalDate.parse("2019-10-15")),
                new ToDo("test test"),
            };
            tasks[0].markDone();
            for (int i = 0; i < tasks.length; i++) {
                assertEquals(tasks[i].toString(), taskList.getTask(i).toString());
            }
        } catch (DukeException e) {
            fail(e);
        }
        taskList = new TaskList();
    }

    @Test
    public void saveDataTest() {
        try {
            taskList.addTask(new ToDo("test test"));
            storage.saveData(taskList);
        } catch (DukeException e) {
            fail("Exception thrown when adding task");
        }
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, "data", "duke.txt");
        File savedData = new File(path.toString());
        try {
            Scanner sc = new Scanner(savedData);
            String writtenData = sc.nextLine();
            assertEquals("T|0|test test", writtenData);
            assertFalse(sc.hasNext());
        } catch (FileNotFoundException e) {
            fail(e);
        }
        taskList = new TaskList();
    }

}
