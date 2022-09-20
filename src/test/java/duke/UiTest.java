package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void welcomeMessageTest() {
        Ui ui = new Ui();
        assertEquals("☺ Hey there! I'm TASKY,\n"
                + "your personal task manager!\n\n"
                + "What can I do for you today?", ui.showWelcome());
    }
}
