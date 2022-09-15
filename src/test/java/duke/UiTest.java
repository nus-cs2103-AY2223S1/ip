package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Ui ui = new Ui();

    @Test
    public void printsLogoCorrectly() {
        assertEquals(ui.showWelcome(), "Hello from\n" + logo + "I'm Duke! \n What can I do for you?");
    }

    @Test
    public void printsExitMessageCorrectly() {
        assertEquals(ui.showExitMessage(), "\nGoodbye! Hope to see you again!\n");
    }

    @Test
    public void addsLineBreakProperly() {
        assertEquals(ui.addLineBreak(""), "\n\n");
    }
}