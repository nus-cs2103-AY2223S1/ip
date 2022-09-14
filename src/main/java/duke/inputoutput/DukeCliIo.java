package duke.inputoutput;

import java.util.Scanner;
import duke.util.StringParser;

/**
 * Class to handle input/output of data
 */
public class DukeCliIo extends DukeAbstractIo {
    private final Scanner scanner;

    /**
     * Initialize the scanner.
     */
    public DukeCliIo() {
        scanner = new Scanner(System.in);
    }

    /**
     * Returns a line entered by the user as <code>String</code>.
     * 
     * @return String
     */
    @Override
    public String readLine() {
        return scanner.nextLine().trim();
    }

    /**
     * {@inheritDoc}
     * 
     * @param txt
     * @param features
     */
    @Override
    public void printTask(String txt, int features) {
        if ((features & DukeCliSettings.INDENT.value) == DukeCliSettings.INDENT.value) {
            txt = StringParser.addIndent(txt);
        }
        if ((features & DukeCliSettings.INDENT.value) == DukeCliSettings.WRAPPER.value) {
            txt = StringParser.addWrapper(txt);
        }

        System.out.println(txt);
    }

    /**
     * {@inheritDoc}
     * 
     * @param txt
     */
    @Override
    public void printTask(String txt) {
        printTask(txt, 3);
    }
}
