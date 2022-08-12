import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String initMsg = "Hello! I'm Duke \n What can I do for you?";
        String byeMsg = "Bye. Hope to see you soon again!";
        Scanner scan = new Scanner(System.in);
        printMessage(initMsg);
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            }
            printMessage(input);
        }
        printMessage(byeMsg);
    }

    public static void printMessage(String s) {
        System.out.println("--------------------------------------");
        System.out.println(s);
        System.out.println("--------------------------------------");
    }
}
