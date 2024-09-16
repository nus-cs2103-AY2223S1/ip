package roofus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void greetTest() {
        Ui ui = new Ui();
        assertEquals("Hello I'm ROOOOFUS!!!\n"
                + "What can I do for you?", ui.greet());
    }
    
    @Test 
    public void signOffTest() {
        Ui ui = new Ui();
        assertEquals( "Bye. Hope to see you again soon!", 
                ui.signOff());
    }
    
    @Test
    public void clearStorageTest() {
        Ui ui = new Ui();
        assertEquals("Storage has been cleared :)", 
                ui.clearStorage());
    }
}
