package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void welcomeMessageTest() {
        String expectedOutput = "Kon'nichiwa, I am Raijinmaru! A Fat Capybara!\n"
                + "How may I assist you today?\n"
                + "Type help for list of commands";
        assertEquals(expectedOutput, Ui.welcomeMessage());
    }
}
