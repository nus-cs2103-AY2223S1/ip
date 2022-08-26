package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void greetCorrectly() {
        Ui ui = new Ui();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        print(ui.greetUi());
        assertEquals("Hello I'm Duke\nWhat can I do for you?", ui.greetUi());
    }
   /* @Test
    public void dummyTest(){
        assertEquals(2,2);
    }
    */

 /*  @Test
   public void dummyTest(){
       assertEquals(2,2);
   }

  */


}
