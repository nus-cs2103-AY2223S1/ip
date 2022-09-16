package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void welcomeMessageTest() {
        Ui ui = new Ui();
        assertEquals("Hello! I'm TASKY\nWhat can I do for you?", ui.showWelcome());
    }
}
