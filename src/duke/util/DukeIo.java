package duke.util;
import java.util.List;
import java.util.Scanner;

public class DukeIo {
    // class to handle input/output of data
    private static final String LINE = "   ____________________________________________________________";
    private static final String EMPTY_LIST = "The current list is empty!";
    private final Scanner scanner;

    public DukeIo() {
        scanner = new Scanner(System.in);
    }

    public String readLine() {
        return scanner.nextLine().trim();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printTask(String txt, int features) {
        // 00 - no wrapper/indent
        // 01 - indent
        // 10 - wrapper
        if ((features & 2) == 2)
            txt = addIndent(txt);
        if ((features & 1) == 1)
            txt = addWrapper(txt);

        System.out.println(txt);
    }

    public void printTask(String txt) {
        printTask(txt, 3);
    }

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

    public void printError(Exception e) {
        printTask(String.format("🙄 OOPS!!! %s", e.getMessage()));
    }

    public static String addIndent(String txt) {
        return "\t" + txt.replaceAll("\n", "\n\t");
    }

    public static String addWrapper(String txt) {
        return String.format("%s%n%s%n%s%n", LINE, txt, LINE);
    }
}
