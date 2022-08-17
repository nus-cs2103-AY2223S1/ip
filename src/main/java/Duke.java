import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final ArrayList<String> botArray = new ArrayList<String>();

    public static void main(String[] args) {
        Greet();
        Scanner userInput = new Scanner(System.in);
        String userReply = userInput.nextLine().strip();
        while(!userReply.toLowerCase().equals("bye")) {
            if (userReply.equals("")) {
                System.out.println("##############################################");
                System.out.println("Please enter some valid text");
                System.out.println("##############################################");
                userReply = userInput.nextLine().strip();
                continue;
            }
            if (userReply.toLowerCase().equals("list")) {
                List();
                userReply = userInput.nextLine().strip();
                continue;
            }
            Add(userReply);
            userReply = userInput.nextLine().strip();
        }
        GoodBye();
    }

    private static void Greet() {
        System.out.println("Hello, this is Siri! It is a pleasure to meet you!");
        System.out.println("How may I assist you?");
        System.out.println("##############################################");
    }


    private static void Add(String userReply) {
        System.out.println("##############################################");
        System.out.println("\t\t\t" + "added: " + userReply);
        System.out.println("##############################################");
        botArray.add(userReply);
    }
    public static void List() {
        System.out.println("##############################################");
        if (botArray.size() == 0) {
            System.out.println("\t\t\t" + "No items are in the list");
        }
        for (int i = 0; i < botArray.size(); i++) {
            int pos = i + 1;
            String itemDisplayed = String.format("\t\t\t%d. %s",pos, botArray.get(i));
            System.out.println(itemDisplayed);
        }
        System.out.println("##############################################");
    }

    private static void GoodBye() {
        System.out.println("##############################################");
        System.out.println("So Long, farewell!");
        System.out.println("##############################################");
    }
}
