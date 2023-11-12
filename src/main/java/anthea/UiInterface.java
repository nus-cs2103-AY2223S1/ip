package anthea;

import java.io.Reader;

/**
 * Functions that a UI interface needs to implement.
 */
public interface UiInterface {
    /**
     * Gives you the input stream to scan to interact with this interface.
     *
     * @return InputStream to scan.
     */
    Reader getReader();

    /**
     * Styles and prints lines with a border.
     *
     * @param lines Lines to be printed
     */
    void printStyledMessage(String... lines);

    /**
     * Greets user.
     */
    void greet();

    /**
     * Leaves the user.
     */
    void leave();
}
