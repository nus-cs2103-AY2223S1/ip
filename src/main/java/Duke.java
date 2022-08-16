import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(welcome);
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.next();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
