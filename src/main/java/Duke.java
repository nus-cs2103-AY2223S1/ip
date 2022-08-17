import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Greet();
        Echo();
        GoodBye();
    }

    private static void Greet() {
        System.out.println("Hello, this is Siri! It is a pleasure to meet you!");
        System.out.println("How may I assist you?");
        System.out.println("##############################################");
    }

    private static void Echo() {
        Scanner userInput = new Scanner(System.in);
        String userReply = userInput.nextLine();
        while (!userReply.toLowerCase().equals("bye")) {
            if (userReply.equals("")) {
                System.out.println("##############################################");
                System.out.println("Please enter a valid command");
                System.out.println("##############################################");
                userReply = userInput.nextLine();
                continue;
            }
            System.out.println("##############################################");
            System.out.println("\t\t\t\t\t" + userReply);
            System.out.println("##############################################");
            userReply = userInput.nextLine();
        }
    }

    private static void GoodBye() {
        System.out.println("##############################################");
        System.out.println("So Long, farewell!");
        System.out.println("##############################################");
    }
}
