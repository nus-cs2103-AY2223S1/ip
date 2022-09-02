package duke;

/**
 * Functions that a UI interface needs to implement.
 */
public interface UiInterface {
    /**
     * Styles and prints lines with a border.
     *
     * @param lines Lines to be printed
     */
    public void printStyledMessage(String... lines);

    /**
     * Greets user.
     */
    public void greet();

    /**
     * Leaves the user.
     */
    public void leave();
}
