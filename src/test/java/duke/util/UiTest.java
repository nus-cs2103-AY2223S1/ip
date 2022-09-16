package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void welcomeMessageTest() {
        String expectedOutput = "________________________________________\n"
                + "Kon'nichiwa, I am Raijinmaru! A Fat Capybara!\n"
                + "How may I assist you today?\n"
                + "Type help for list of commands\n"
                + "________________________________________";
        assertEquals(expectedOutput, Ui.welcomeMessage());
    }
}
