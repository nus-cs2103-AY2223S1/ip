import java.util.Scanner;

class DukeIO {
    // class to handle input/output of data
    private static final String LINE = "   ____________________________________________________________";
    private final Scanner scanner;

    DukeIO() {
        scanner = new Scanner(System.in);
    }

    String readLine() {
        return scanner.nextLine().trim();
    }

    void printLine() {
        System.out.println(LINE);
    }

    void printTask(String txt, int features) {
        // 00 - no wrapper/indent
        // 01 - indent
        // 10 - wrapper
        if ((features & 2) == 2)
            txt = addIndent(txt);
        if ((features & 1) == 1)
            txt = addWrapper(txt);

        System.out.println(txt);
    }

    void printTask(String txt) {
        printTask(txt, 3);
    }

    static String addIndent(String txt) {
        return "\t" + txt.replaceAll("\n", "\n\t");
    }

    static String addWrapper(String txt) {
        return String.format("%s%n%s%n%s%n", LINE, txt, LINE);
    }
}
