package stashy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Test class for Ui.
 */
public class UiTest {
    @Test
    public void showErrorString_success() {
        assertEquals("☹ BEEP BOOP BUZZ ERROR!!!\nThis is a test", (new Ui()).showErrorString("This is a test"));
    }

    @Test
    public void showGoodbyeString_success() {
        assertEquals("Good bye then, see you some time! - Stashy, 2022", (new Ui()).showGoodbyeString());
    }
}
