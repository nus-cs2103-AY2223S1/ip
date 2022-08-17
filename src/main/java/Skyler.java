import java.util.Scanner;

public class Skyler {
    public static void main(String[] args) {
        // declare scanner object and initialise with
        // predefined standard input object
        Scanner sc = new Scanner(System.in);

        System.out.println("Good day, mate! I'm Skyler\nHow can I help you?\n");

        while(sc.hasNext()) {
            String action = sc.nextLine();
            if (action.equals("bye")) {
                System.out.println("Bye! See you again soon!");
                break;
            } else {
                System.out.println(action);
            }
        }

        sc.close();
    }
}
