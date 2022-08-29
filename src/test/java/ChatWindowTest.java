import general.ui.ChatWindow;
import general.ui.Span;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChatWindowTest {
    private static final int WINDOW_SIZE = 50;
    private static final String VERY_LONG_TEXT = "This is a very long text that will make it very likely " +
            "to wrap across multiple lines. Moreover, this text contains multiple sentences.";
    private static final String SHORT_TEXT = "Hello, world!";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void simpleCommandTest() {
        final ChatWindow chatWindow = new ChatWindow(WINDOW_SIZE);
        final String expectedOutput = "╭───────────────╮\n" +
                                      "│ Hello, world! │\n" +
                                      "╰┬╭─────────────╯\n" +
                                      " ╰╯ You          \n";
        chatWindow.printCommand(new Span(SHORT_TEXT));
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void simpleResponseTest() {
        final ChatWindow chatWindow = new ChatWindow(WINDOW_SIZE);
        final String expectedOutput = "                                 ╭───────────────╮\n" +
                                      "                                 │ Hello, world! │\n" +
                                      "                                 ╰─────────────╮┬╯\n" +
                                      "                                           MIA ╰╯ \n";
        chatWindow.printResponse(new Span(SHORT_TEXT));
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void commandWrapTest() {
        final ChatWindow window = new ChatWindow(WINDOW_SIZE);
        final String expectedOutput = "╭────────────────────────────────────╮\n" +
                                      "│ This is a very long text that will │\n" +
                                      "│ make it very likely to wrap across │\n" +
                                      "│ multiple lines. Moreover, this     │\n" +
                                      "│ text contains multiple sentences.  │\n" +
                                      "╰┬╭──────────────────────────────────╯\n" +
                                      " ╰╯ You                               \n";
        window.printCommand(new Span(VERY_LONG_TEXT));
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void responseWrapTest() {
        final ChatWindow window = new ChatWindow(WINDOW_SIZE);
        final String expectedOutput = "            ╭────────────────────────────────────╮\n" +
                                      "            │ This is a very long text that will │\n" +
                                      "            │ make it very likely to wrap across │\n" +
                                      "            │ multiple lines. Moreover, this     │\n" +
                                      "            │ text contains multiple sentences.  │\n" +
                                      "            ╰──────────────────────────────────╮┬╯\n" +
                                      "                                           MIA ╰╯ \n";
        window.printResponse(new Span(VERY_LONG_TEXT));
        assertEquals(expectedOutput, outContent.toString());
    }
}
