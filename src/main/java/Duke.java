import java.util.Scanner;

public class Duke {

    public final static String initMsg = "Hello! I'm Duke \n What can I do for you?";
    public final static String byeMsg = "Bye. Hope to see you soon again!";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] lst = new String[100];
        int idx = 0;
        printMessage(initMsg);
        while (true) {
            String input = scan.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList(lst);
            } else {
                lst[idx] = input;
                printMessage("added: " + input);
                idx += 1;
            }
        }
        printMessage(byeMsg);
    }

    public static void printMessage(String s) {
        System.out.println("--------------------------------------");
        System.out.println(s);
        System.out.println("--------------------------------------");
    }

    public static void printList(String[] lst) {
        System.out.println("--------------------------------------");
        int idx = 0;
        while (true) {
            if (lst[idx] == null) {
                break;
            }
            System.out.println((idx + 1) + "." + lst[idx]);
            idx += 1;
        }
        System.out.println("--------------------------------------");
    }
}
