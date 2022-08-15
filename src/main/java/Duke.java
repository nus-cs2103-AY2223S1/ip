import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        wrapPrint(greeting);
        repeat();
    }

    private static void wrapPrint(String toPrint) {
        System.out.println("    ____________________________________________________________");
        System.out.println(toPrint);
        System.out.println("    ____________________________________________________________");
    }

    private static String leftPad(String toPrint) {
        return "     " + toPrint;
    }

    private static void repeat() {
        String input = "";
        Scanner in = new Scanner(System.in);
        while(true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                wrapPrint("     Bye. Hope to see you again soon!");
                break;
            }
            wrapPrint(leftPad(input));
        }
    }
}
