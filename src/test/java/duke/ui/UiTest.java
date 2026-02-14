package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UiTest {

    private Ui ui = new Ui(System.in, System.out);

    @Test
    public void testBidFarewell() {
        String expected = "So ends our discourse.\n"
                + "I shall remember what you have entrusted to me.\n";
        assertEquals(expected, ui.bidFarewell());
    }

    @Test
    public void testAskingForClarification() {
        String expected =
                "Your words are unclear.\n"
            + "Speak plainly, or consult 'help'.\n";
        assertEquals(expected, ui.askForClarification());
    }
}
