import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Control dc = new Control();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        String message = sc.nextLine();

        while (!message.equals("bye")) {
            try {
                dc.eval(message);
            } catch (DukeException e){
                System.out.println(e);
            }
            message = sc.nextLine();
        }

        System.out.println("\nBye. Hope to see you again soon!");
        sc.close();
        }
    }
