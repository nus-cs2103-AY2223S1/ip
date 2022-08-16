import java.util.Scanner;

public class Lish {

    public static void printResponse(String response) {
        System.out.println("    ____________________________________________________________\n");
        System.out.println("    " + response);
        System.out.println("    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Hello! I'm Lish\n" + "    What can I do for you?\n";
        printResponse(greeting);

        while (true) {
            String input = sc.next();
            printResponse(input);
            if (input.equals("bye")) {
                break;
            }
        }
    }
}
