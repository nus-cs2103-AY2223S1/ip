import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hi, I'm Duke. What can I do for you?");
        Scanner keyboard = new Scanner(System.in);
        String message = keyboard.nextLine();
        while (!message.equals("bye")) {
            System.out.println(message);
            message = keyboard.nextLine();
        }
        System.out.println("Bye! See you next time!");
    }
}
