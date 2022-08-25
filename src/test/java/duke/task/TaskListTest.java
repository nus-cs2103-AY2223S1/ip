package duke.task;

import duke.logic.Storage;
import duke.logic.TaskList;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private static Storage storage = new Storage("./test/tasklisttest.txt");
    static {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(storage.getHistory());
            //prepare what to overwrite
            String overwrite = "T01_1\n"
                    + "D11_22022-02-02\n"
                    + "E01_32033-03-03\n";
            fileWriter.write(overwrite);
            fileWriter.close();
        } catch (IOException e) {
            //made directory and/or made file could be deleted by user during runtime of this program
            throw new RuntimeException(e);
        }
    }

    @Test
    public void retrieve() {
        TaskList taskList = new TaskList(storage);
        try {
            taskList.retrieve();
        } catch (IOException e) {
            throw new RuntimeException();
        }

        assertEquals("[T][ ] 1", taskList.get(0).toString());

        assertEquals("[D][X] 2 (by: Feb 2 2022)", taskList.get(1).toString());

        assertEquals("[E][ ] 3 (at: Mar 3 2033)", taskList.get(2).toString());

    }
}
