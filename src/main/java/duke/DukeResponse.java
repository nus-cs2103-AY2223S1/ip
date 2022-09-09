package duke;

import java.util.function.Consumer;

/**
 * Stores an output to print once.
 */
public class DukeResponse {
    private Consumer<UiInterface> responsePrinter;
    private boolean hasPrinted = false;

    /**
     * Constructs a wrapper for the output,
     * so it prints only once.
     *
     * @param output Output to print only once.
     */
    public DukeResponse(String... output) {
        responsePrinter = (ui) -> ui.printStyledMessage(output);
    }

    /**
     * Constructs a wrapper for the output,
     * so it prints only once.
     *
     * @param printer Method to print only once.
     */
    public DukeResponse(Consumer<UiInterface> printer) {
        this.responsePrinter = printer;
    }

    /**
     * Prints the output if not printed yet.
     *
     * @param ui User interface to use.
     */
    public void print(UiInterface ui) {
        if (hasPrinted) {
            return;
        }
        responsePrinter.accept(ui);
        hasPrinted = true;
    }
}
