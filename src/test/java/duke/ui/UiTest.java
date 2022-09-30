package duke.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    Ui ui = new Ui(System.in, System.out);

    @Test
    public void testBidFarewell(){
        String expected = "Ah! And so we part here today."
                + "\n We may yet meet again...Farewell, my friend!\n";
        assertEquals(expected, ui.bidFarewell());
    }

    @Test
    public void testAskingForClarification(){
        String expected = "Did you say..." + "I AM TESTING THIS" + "?\n"
                + "The shadow of my memory is long...State what you would ask clearly.\n";
        assertEquals(expected, ui.askForClarification("I AM TESTING THIS"));
    }
}
