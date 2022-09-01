package neo;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DeadlineTest {
    @Test
    public void DeadlineString() {
        assertEquals("[D][ ] return book (by: Feb 02 2022)", new Deadline("return book", "2022-02-02").toString());
    }

    @Test
    public void DeadlineMark() throws NeoException, IOException {
        Deadline d = new Deadline("return book", "2022-02-02");
        Ui ui = new Ui();
        Storage stor = new Storage();
        TaskList arrayLL = new TaskList();
        arrayLL.addTask(d);
        MarkCommand mc = new MarkCommand(ui, stor, arrayLL);
        mc.complete("1");
        assertEquals("X", arrayLL.getTask(0).getIsDone());
    }

    @Test
    public void DeadlineUnMark() throws NeoException, IOException {
        Deadline d = new Deadline("return book", "2022-02-02");
        Ui ui = new Ui();
        Storage stor = new Storage();
        TaskList arrayLL = new TaskList();
        arrayLL.addTask(d);
        UnMarkCommand mc = new UnMarkCommand(ui, stor, arrayLL);
        mc.complete("1");
        assertEquals(" ", arrayLL.getTask(0).getIsDone());
    }
}

