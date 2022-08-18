import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // Printables
    private static final String TAB = "    ";
    private static final String LINE = String
            .format("%s%s", TAB, "____________________________________________________________");
    private static final String WELCOMEMESSAGE = String
            .format("Hello! I'm Duke\n%s What can I do for you?", TAB);
    private static final String ENDMESSAGE = "Bye. Hope to see you again soon!";

    // Commands
    private static final String ENDCOMMAND = "bye";
    private static final String PRINTCOMMAND = "list";

    private static final List<String> list = new ArrayList<>();
    /**
     * Adds param str to a List.
     * @param str
     */
    private static void store(String str) {
        list.add(str);
        prettyPrint(String.format("added: %s", str));
    }

    /**
     * Creates a new Collection that has each entry numbered in ascending order
     */
    private static void printAll() {
        List<String> printables = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String item = list.get(i);
            int index = i + 1;
            printables.add(String.format("%d: %s", index, item));
        }
        prettyPrint(printables);
    }

    private static void prettyPrint() {
        prettyPrint("");
    }

    private static void prettyPrint(String printable) {
        String out = String.format("%s\n%s %s\n%s", LINE, TAB, printable, LINE);
        System.out.println(out);
    }

    private static void prettyPrint(List<String> printables) {
        if (printables.size() == 0) {
            prettyPrint();
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < printables.size(); i++) {
            String s = printables.get(i);
            if (i == 0) {
                sb.append(String.format("%s\n", s));
            }
            else if (i == printables.size() - 1) {
                sb.append(String.format("%s %s", TAB, s));
            }
            else {
                sb.append(String.format("%s %s\n", TAB, s));
            }
        }
        String printable = sb.toString();
        prettyPrint(printable);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prettyPrint(WELCOMEMESSAGE);
        while (true) {
            String input = sc.nextLine();
            if (input.equals(ENDCOMMAND)) {
                break;
            }
            if (input.equals(PRINTCOMMAND)) {
                printAll();
                continue;
            }
            store(input);
        }
        prettyPrint(ENDMESSAGE);
    }
}
