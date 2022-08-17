import java.util.Scanner;

import org.apache.commons.text.WordUtils;

public class Duke {

    private static final String LINE = "──────────────────────────────────────────";

    public static String prettifyStr(String str) {
        return String.format("\t%s\n\t %s\n\t%s", LINE,
                WordUtils.wrap(str, 40, "\n\t ", false), LINE);
    }

    /**
     * Starts the program.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(prettifyStr("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(prettifyStr(input));
        }
        sc.close();
    }
}
