import java.util.Scanner;

public class Duke {

    public static void printLine() {
        System.out.println("\t" + "____________________________________________________________");
    }

    public static void greet() {
        printLine();
        String logo = "\t" + "  ____        _        \n"
                + "\t" + " |  _ \\ _   _| | _____ \n"
                + "\t" + " | | | | | | | |/ / _ \\\n"
                + "\t" + " | |_| | |_| |   <  __/\n"
                + "\t" + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + " Hello from\n" + logo);
        System.out.println("\t" +" Hello! I'm Duke\n");
        System.out.println("\t" +" What can I do for you?");
        printLine();
    }

    public static void echo(String toEcho) {
        printLine();
        System.out.println("\t" + " " + toEcho);
        printLine();
    }

    public static boolean isExit(String text) {
        return text.equals("bye");
    }

    public static void exit() {
        printLine();
        System.out.println("\t" +" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (isExit(input)) {
                exit();
                break;
            } else {
                echo(input);
            }
        }
    }
}
