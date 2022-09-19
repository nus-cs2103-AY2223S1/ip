package duke.inputoutput;

import java.util.function.Consumer;

/**
 * Duke IO class to handle interactions with the GUI
 */
public class DukeGuiIo extends DukeAbstractIo {

    private final Consumer<String> makeChild;
    private final Consumer<String> makeWarningChild;

    /**
     * Create an instance of the gui interaction object specifying a way to make a
     * new child and a warning child
     *
     * @param makeChild        Consumer to add a normal node
     * @param makeWarningChild Consumer to add a warning node
     */
    public DukeGuiIo(Consumer<String> makeChild, Consumer<String> makeWarningChild) {
        this.makeChild = makeChild;
        this.makeWarningChild = makeWarningChild;
    }

    @Override
    public void printTask(String txt, int features) {
        makeChild.accept(txt);
    }

    @Override
    public void printTask(String txt) {
        printTask(txt, DukeCliSettings.INDENT);
    }

    @Override
    public String readLine() {
        return null;
    }

    @Override
    public void printError(Exception e) {
        makeWarningChild.accept(String.format("😠 OOPS!!!%n%s", e.getMessage()));
    }
}
