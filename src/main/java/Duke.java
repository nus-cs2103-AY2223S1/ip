import java.util.*;

public class Duke {
    private static final String TAB = "    ";
    private static final String LINEBREAK = "______________________________";

    public static void main(String[] args) {
        greet();
        echo();
    }

    private static void printTab(String content) {
        System.out.println(TAB + content);
    }

    public static void greet() {
        printTab(LINEBREAK);
        printTab("Hello! I'm Duke");
        printTab("What can I do for you?");
        printTab(LINEBREAK);
        System.out.println();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                printTab(LINEBREAK);
                printTab("Bye. Hope to see you again soon!");
                printTab(LINEBREAK);
                System.out.println();
                break;
            } else {
                printTab(LINEBREAK);
                printTab(s);
                printTab(LINEBREAK);
                System.out.println();
            }
        }
    }
}
