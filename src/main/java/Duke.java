import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Snoopy\n");
        System.out.println("What can I do for you?\n");
        String response = myObj.nextLine();
        while (!response.equals("bye")) {
            System.out.println(response);
            response = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
