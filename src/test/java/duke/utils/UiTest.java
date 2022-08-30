package duke.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private static final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    private static String prettyPrintString(String msg) {
        StringBuilder results = new StringBuilder();
        results.append("    ____________________________________________________________\n");
        String[] msgTokens = msg.split("\n");
        for (String token : msgTokens) {
            String indentedToken = "     " + token + "\n";
            results.append(indentedToken);
        }
        results.append("    ____________________________________________________________\n\n");
        return results.toString();
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void sayGreetingsTest() {
        String greetingMsg = UiTest.prettyPrintString("Hello! I'm Duke!\nWhat can I do for you?");

        Ui ui = new Ui();
        ui.sayGreetings();
        assertEquals(greetingMsg, outputStreamCaptor.toString());
    }

    @Test
    public void sayGoodbyeTest() {
        String goodByeMsg = UiTest.prettyPrintString("Bye. Hope to see you again soon!");

        Ui ui = new Ui();
        ui.sayGoodBye();
        assertEquals(goodByeMsg, outputStreamCaptor.toString());
    }

    @Test
    public void prettyPrintSingleLineTest() {
        String singleLine = UiTest.prettyPrintString("hi");

        Ui ui = new Ui();
        ui.prettyPrint("hi");
        assertEquals(singleLine, outputStreamCaptor.toString());
    }

    @Test
    public void prettyPrintMultipleLinesTest() {
        String singleLine = UiTest.prettyPrintString("hi\nhi\nhi\nhi\nhi\nhi\nhi\nhi\nhi\n");

        Ui ui = new Ui();
        ui.prettyPrint("hi\nhi\nhi\nhi\nhi\nhi\nhi\nhi\nhi\n");
        assertEquals(singleLine, outputStreamCaptor.toString());
    }

    @Test
    public void prettyPrintLongLineTest() {
        String singleLine = UiTest.prettyPrintString("But I must explain to you how all this mistaken idea "
                + "of denouncing pleasure and praising pain was born and I will give you a complete account of the "
                + "system, and expound the actual teachings of the great explorer of the truth, the master-builder "
                + "of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, "
                + "but because those who do not know how to pursue pleasure rationally encounter consequences that "
                + "are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain "
                + "of itself, because it is pain, but because occasionally circumstances occur in which toil and "
                + "pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes "
                + "laborious physical exercise, except to obtain some advantage from it? But who has any right to "
                + "find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one "
                + "who avoids a pain that produces no resultant pleasure?");
        Ui ui = new Ui();
        ui.prettyPrint("But I must explain to you how all this mistaken idea "
                + "of denouncing pleasure and praising pain was born and I will give you a complete account of the "
                + "system, and expound the actual teachings of the great explorer of the truth, the master-builder "
                + "of human happiness. No one rejects, dislikes, or avoids pleasure itself, because it is pleasure, "
                + "but because those who do not know how to pursue pleasure rationally encounter consequences that "
                + "are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain pain "
                + "of itself, because it is pain, but because occasionally circumstances occur in which toil and "
                + "pain can procure him some great pleasure. To take a trivial example, which of us ever undertakes "
                + "laborious physical exercise, except to obtain some advantage from it? But who has any right to "
                + "find fault with a man who chooses to enjoy a pleasure that has no annoying consequences, or one "
                + "who avoids a pain that produces no resultant pleasure?");
        assertEquals(singleLine, outputStreamCaptor.toString());
    }

    @AfterEach
    public void tearDown() {
        outputStreamCaptor.reset();
        System.setOut(standardOut);
    }
}
