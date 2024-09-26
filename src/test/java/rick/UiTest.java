package rick;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    public Ui ui = new Ui();

    @Test
    public void showWelcome_showsCorrectWelcomeMessage() {
        assertEquals(ui.showWelcome(), "Hello, I'm Rick! \n What can I do for you?");
    }

    @Test
    public void showExitMessage_showsExitMessageCorrectly() {
        assertEquals(ui.showExitMessage(), "\nGoodbye Morty! Hope to see you again!\n");
    }

    @Test
    public void addLineBreak_addsLineBreakToTextCorrectly() {
        assertEquals(ui.addLineBreak(""), "\n\n");
    }
}