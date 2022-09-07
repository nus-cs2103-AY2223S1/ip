package doke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void processString_byeString_falseReturned() {
        Storage storage = new Storage();
        Ui ui = new Ui();
        TaskList taskList = new TaskList(ui, storage);
        Parser parser = new Parser();
        assertEquals(false, parser.processString(taskList, storage, "bye", ui));
    }
}
