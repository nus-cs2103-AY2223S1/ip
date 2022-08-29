package jUnitTests;

import Duke.ui.UI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckValidInputTest {

    @Test
    void shouldCheckValidInput() {
        UI ui = new UI();
        assertAll(() -> assertFalse(ui.checkValid(" ")),
                () -> assertFalse(ui.checkValid("deadline")),
                () -> assertTrue(ui.checkValid("mark 1")),
                () -> assertTrue(ui.checkValid("deadline read book /by Monday")));
    }
}
