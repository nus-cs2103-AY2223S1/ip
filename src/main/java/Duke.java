import java.util.Scanner;

public class Duke {
    private static String lastLine = "";
    public static void main(String[] args) {
        System.out.println("My name is [insert name here], how can I help?");
        while (true) {
            listen();
            if (lastLine.equals("bye")) {
                System.out.println("See you next time...");
                break;
            }
            echoLast();
        }
    }

    private static void listen() {
        Scanner sc = new Scanner(System.in);
        lastLine = sc.nextLine();
    }

    private static void echoLast() {
        System.out.println(lastLine);
    }
}