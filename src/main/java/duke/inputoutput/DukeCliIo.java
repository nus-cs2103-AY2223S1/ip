package duke.inputoutput;

import java.util.List;
import java.util.Scanner;

/**
 * Class to handle input/output of data
 */
public class DukeCliIo implements DukeIo {
    private static final String LINE = "   ____________________________________________________________";
    private static final String EMPTY_LIST = "The current list is empty!";
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
     * Prints a Line to the screen
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * {@inheritDoc}
     * 
     * @param txt
     * @param features
     */
    @Override
    public void printTask(String txt, int features) {
        // 00 - no wrapper/indent
        // 10 - indent
        // 01 - wrapper
        if ((features & 2) == 2) {
            txt = addIndent(txt);
        }
        if ((features & 1) == 1) {
            txt = addWrapper(txt);
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

    /**
     * {@inheritDoc}
     * 
     * @param <U>
     * @param list
     */
    @Override
    public <U> void printList(List<U> list) {
        if (list.isEmpty()) {
            printTask(EMPTY_LIST);
            return;
        }
        printLine();
        for (int i = 0; i < list.size(); ++i) {
            printTask(String.format("%d. %s", i + 1, list.get(i)), 2);
        }
        printLine();
    }

    /**
     * {@inheritDoc}
     * 
     * @param <U>
     * @param list
     */
    @Override
    public <U> void printList(U[] list) {
        if (list.length == 0) {
            printTask(EMPTY_LIST);
            return;
        }
        printLine();
        for (int i = 0; i < list.length; ++i) {
            printTask(String.format("%d. %s", i + 1, list[i]), 2);
        }
        printLine();
    }

    /**
     * {@inheritDoc}
     * 
     * @param e
     */
    @Override
    public void printError(Exception e) {
        printTask(String.format("🙄 OOPS!!! %s", e.getMessage()));
    }

    /**
     * Returns txt indented at every linebreak.
     * 
     * @param txt Text to indent
     * @return String
     */
    public static String addIndent(String txt) {
        return "\t" + txt.replaceAll("\n", "\n\t");
    }

    /**
     * Returns text wrapped between 2 lines.
     * 
     * @param txt Text to wrapped between 2 lines
     * @return String
     */
    public static String addWrapper(String txt) {
        return String.format("%s%n%s%n%s%n", LINE, txt, LINE);
    }
}
