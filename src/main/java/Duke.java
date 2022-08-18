import java.util.Scanner;

public class Duke {
    static String SEPARATING_LINE = "    ____________________________________________________________";

    private static void FormatPrint(String s) {
        System.out.println(SEPARATING_LINE);
        System.out.println(s);
        System.out.println(SEPARATING_LINE);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String OPENING = "    Hello! I'm Duke\n    What can I do for you?";
        String END_WORD = "bye";
        String ENDING = "    Bye. Hope to see you again soon!";

        // opening
        Duke.FormatPrint(OPENING);

        // respond to the input
        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.nextLine();
            str = str.replace("\n", "").replace("/r", "");

            // System.out.print("Original String:\n" + str + "\n");

            if (str.equals(END_WORD)) {
                Duke.FormatPrint(ENDING);
                break;
            } else {
                Duke.FormatPrint(str);
            }
        }
    }
}
