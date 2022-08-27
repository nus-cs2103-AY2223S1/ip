package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTodoCommand(){
        Parser parser = new Parser();
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        StorageStub storage = new StorageStub();

        parser.parse("todo first", ui, taskList, storage);

        assertEquals(taskList.toString(), "1. [T][ ] first");
    }

    @Test
    public void parseDeleteCommand(){
        Parser parser = new Parser();
        Ui ui = new Ui();
        TaskList taskList = new TaskList();
        StorageStub storage = new StorageStub();

        parser.parse("todo first", ui, taskList, storage);
        parser.parse("delete 1", ui, taskList, storage);

        assertEquals(taskList.toString(), "");
    }
}
