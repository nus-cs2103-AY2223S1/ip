import java.util.Scanner;

public class Duke {
    private static final String TAB = "    ";
    private static final String LINE = String
            .format("%s%s", TAB, "____________________________________________________________");
    private static final String WELCOMEMESSAGE = String
            .format("Hello! I'm Duke\n%s What can I do for you?", TAB);
    private static final String ENDMESSAGE = "Bye. Hope to see you again soon!";

    private static final String ENDCOMMAND = "bye";

    private static void prettyPrint(String printable) {
        String out = String.format("%s\n%s %s\n%s", LINE, TAB, printable, LINE);
        System.out.println(out);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        prettyPrint(WELCOMEMESSAGE);
        while (true) {
            String input = sc.nextLine();
            if (input.equals(ENDCOMMAND)) {
                break;
            }
            prettyPrint(input);
        }
        prettyPrint(ENDMESSAGE);
    }
}
