package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void welcomeMessageTest() {
        String logo = " _           _        \n"
                + "| |    _   _| | _____ \n"
                + "| |   | | | | |/ / _ \\\n"
                + "| |___| |_| |   <  __/\n"
                + "|_____|\\__,_|_|\\_\\___|\n";

        String expectedOutput = "________________________________________\n"
                + "Hola Amigo! My name is\n"
                + logo
                + "\n"
                + "How may I assist you today?\n"
                + "________________________________________";
        Ui ui = new Ui();
        assertEquals(expectedOutput, ui.welcomeMessage());
    }
}
