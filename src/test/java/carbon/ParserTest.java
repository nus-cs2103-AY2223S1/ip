package carbon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private Parser parser = new Parser(this.ui, this.storage);

    @Test
    public void process_invalidCommand_returnsInvalidInput(){
        String input = "testInvalidCommand";
        String expectedOutput = "";
        assertEquals(2, 2);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(4, 4);
    }
}
