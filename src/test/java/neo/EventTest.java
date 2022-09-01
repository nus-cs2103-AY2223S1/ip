package neo;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class EventTest {
    @Test
    public void EventString() {
        assertEquals("[E][ ] project meeting (on: May 06 2022)", new Event("project meeting", "2022-05-06").toString());
    }

    @Test
    public void EventMark() throws NeoException, IOException {
        Event e = new Event("project meeting", "2022-05-06");
        Ui ui = new Ui();
        Storage stor = new Storage();
        TaskList arrayLL = new TaskList();
        arrayLL.addTask(e);
        MarkCommand mc = new MarkCommand(ui, stor, arrayLL);
        mc.complete("1");
        assertEquals("X", arrayLL.getTask(0).getIsDone());
    }

    @Test
    public void EventUnMark() throws NeoException, IOException {
        Event e = new Event("return book", "2022-05-06");
        Ui ui = new Ui();
        Storage stor = new Storage();
        TaskList arrayLL = new TaskList();
        arrayLL.addTask(e);
        UnMarkCommand mc = new UnMarkCommand(ui, stor, arrayLL);
        mc.complete("1");
        assertEquals(" ", arrayLL.getTask(0).getIsDone());
    }
}

