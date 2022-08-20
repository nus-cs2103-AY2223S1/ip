import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Control dc = new Control();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        String message = sc.nextLine();

        while (!message.equals("bye")) {

            dc.eval(message);
            message = sc.nextLine();
            }

        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
        }
    }
