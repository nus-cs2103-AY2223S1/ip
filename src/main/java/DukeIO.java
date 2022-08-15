import java.util.Scanner;

class DukeIO {
    // class to handle input/output of data
    private static final String LINE = "____________________________________________________________";
    private final Scanner scanner;

    DukeIO() {
        scanner = new Scanner(System.in);
    }

    String readLine() {
        return scanner.nextLine().trim();
    }

    void printTask(String txt, int features) {
        // 00 - no wrapper/indent
        // 01 - indent
        // 10 - wrapper
        if ((features & 1) == 1)
            txt = addWrapper(txt);
        if ((features & 2) == 2)
            txt = addIndent(txt);

        System.out.println(txt);
    }

    void printTask(String txt) {
        printTask(txt, 3);
    }

    static String addIndent(String txt) {
        return txt.replaceAll("^|\n", "\n\t");
    }

    static String addWrapper(String txt) {
        return String.format("%s%n%s%n%s", LINE, txt, LINE);
    }

}
