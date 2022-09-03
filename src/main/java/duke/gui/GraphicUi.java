package duke.gui;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.Reader;

import duke.UiInterface;

/**
 * Used for GUI.
 */
public class GraphicUi implements UiInterface {
    private static PipedWriter writer = new PipedWriter();
    private static final BufferedWriter bufferedWriter = new BufferedWriter(writer);

    /**
     * Gets writer that writes out of application logic into GUI.
     *
     * @return Writer from application logic.
     */
    public static PipedWriter getWriter() {
        return writer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reader getReader() {
        try {
            return new PipedReader(MainWindow.getWriter());
        } catch (IOException e) {
            System.out.println("Error: Did not obtain GUI reader.");
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints lines.
     *
     * @param lines Line to be printed
     */
    @Override
    public void printStyledMessage(String... lines) {
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            result.append(line);
            result.append('\n');
        }
        result.append('\n');
        try {
            bufferedWriter.write(result.toString(), 0, result.length());
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println("Error: Cannot write output.");
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void greet() {
        printStyledMessage("...where is this again?",
                "Oh, hello, I didn't see you there - I'm Anthea, a chatbot...",
                "...or at least that's what they told me.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void leave() {
        printStyledMessage("It was nice to have you around, I'm going back to sleep...",
                "(close the window yourself)");
    }
}
