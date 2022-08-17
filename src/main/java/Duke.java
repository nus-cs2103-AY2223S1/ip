import java.util.Scanner;

public class Duke {
    public static void printBot(String s) {
        String lineBreak = "____________________________________________________________";
        System.out.println(lineBreak);
        System.out.println(s);
        System.out.println(lineBreak);
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Nuke");
        System.out.println("What can I do for you?");
        String s;
        while(true) {
            s = scanner.nextLine();
            if (s.equals("bye")) {
                printBot("Bye. Hope to see you again soon!");
                return;
            } else {
                printBot(s);
            }
        }
    }
}
