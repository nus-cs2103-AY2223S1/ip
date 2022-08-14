import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("_____________________________________________________________");
        System.out.printf("Hey there! I'm Bob\nWhat can I do for you?\n");
        System.out.println("_____________________________________________________________");

        while (sc.hasNext()) {
            String input = sc.next();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
                System.out.println("_____________________________________________________________");
                System.out.println();
            }
        }

        sc.close();
    }
}
