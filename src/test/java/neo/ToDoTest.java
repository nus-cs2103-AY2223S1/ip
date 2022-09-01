package neo;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ToDoTest {
    @Test
    public void ToDoString() {
        assertEquals("[T][ ] borrow book", new ToDo("borrow book").toString());
    }

    @Test
    public void ToDoMark() throws NeoException, IOException {
        ToDo td = new ToDo("project meeting");
        Ui ui = new Ui();
        Storage stor = new Storage();
        TaskList arrayLL = new TaskList();
        arrayLL.addTask(td);
        MarkCommand mc = new MarkCommand(ui, stor, arrayLL);
        mc.complete("1");
        assertEquals("X", arrayLL.getTask(0).getIsDone());
    }

    @Test
    public void ToDoUnMark() throws NeoException, IOException {
        ToDo td = new ToDo("return book");
        Ui ui = new Ui();
        Storage stor = new Storage();
        TaskList arrayLL = new TaskList();
        arrayLL.addTask(td);
        UnMarkCommand mc = new UnMarkCommand(ui, stor, arrayLL);
        mc.complete("1");
        assertEquals(" ", arrayLL.getTask(0).getIsDone());
    }
}

