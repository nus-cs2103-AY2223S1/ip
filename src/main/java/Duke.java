import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm SoCCat \nWhat can I do for you?");
        Scanner userInput = new Scanner(System.in);
        String userName = userInput.nextLine();

        while (!userName.equals("bye")) {
            System.out.println(userName);
            userName = userInput.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}
