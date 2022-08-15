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
        String[] list = new String[100];
        int i = 0;
        String input = "";
        Scanner in = new Scanner(System.in);
        while(true) {
            input = in.nextLine();
            if (input.equals("bye")) {
                wrapPrint("     Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                StringBuilder listString = new StringBuilder();
                for (int j = 0; j < i; j ++) {
                    listString.append(leftPad(String.format("%d. ", j + 1)));
                    listString.append(list[j]);
                    listString.append("\n");
                }
                if (listString.length() > 0) {
                    listString.setLength(listString.length() - 1);
                }
                wrapPrint(listString.toString());
            } else {
                list[i] = input;
                i++;
                wrapPrint(leftPad("added: " + input));
            }
        }
    }
}
