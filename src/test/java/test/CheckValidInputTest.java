package test;

import org.junit.jupiter.api.Test;

import duke.ui.UI;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
