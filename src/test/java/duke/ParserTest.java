package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseTypingInputTest1(){
        String[] strArray = Parser.parseTypingInput("Bye");
        int length = strArray.length;
        assertEquals(1, length);
        }

    @Test
    public void parseTypingInputTest2(){
        String[] strArray = Parser.parseTypingInput("Bye");
        assertEquals("bye", strArray[0]);
    }
}
