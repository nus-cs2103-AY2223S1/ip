package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {



    @Test
    public void parserTest(){
        Storage storage = new Storage("./data/duke.txt");
        Ui ui = new Ui(new Duke());
        TaskList taskList = new TaskList(storage.getTasks(), ui, storage);
        ui.updateTaskList(taskList);
        Parser parser = new Parser(taskList);
        try {
            assertEquals(parser.getCommand("list"), Command.LIST);
            assertEquals(parser.getCommand("bye"), Command.BYE);
            assertEquals(parser.getCommand("todo"), Command.TODO);
            assertEquals(parser.getCommand("unmark"), Command.UNMARK);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void markTest(){
        Todo todo = new Todo("test");

        assertEquals(todo.toString(), "[T][ ] test");
        todo.mark();
        assertEquals(todo.toString(), "[T][X] test");
    }

    @Test
    public void encodeTest() {
        try {
            Deadline deadline = new Deadline("test", "2000-01-01");
            assertEquals(deadline.encode(), "D # not done # test # 2000-01-01");
        } catch (DukeException e) {
            System.out.println(e);
        }



    }
}
