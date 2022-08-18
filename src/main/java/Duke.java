import java.util.Scanner;

public class Duke {
    private static void printSeparator() {
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    
    private static void printMsg(String msg) {
        printSeparator();
        System.out.println("     " + msg);
        printSeparator();
    }
    
    private static void printMultiMsg(String[] msgs) {
        printSeparator();
        for (String msg : msgs) {
            System.out.println("     " + msg);
        }
        printSeparator();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printMultiMsg(new String[]{"Hello my name is Duke", "What can I do for you?"});

        Scanner sc = new Scanner (System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                printMsg("Bye. Hope to see you again soon!");
                break;
            }
            printMsg(input);
        }
    }
}
