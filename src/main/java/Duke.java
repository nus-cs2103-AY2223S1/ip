import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> userCommands = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        askUser();
        sayBye();
    }

    public static void greetUser() {
        System.out.println("\t______________________________________________________");
        System.out.println("\tHey there! I'm Arias!");
        System.out.println("\tHow may I help you? :)");
        System.out.println("\t______________________________________________________\n");
    }

    public static void sayBye() {
        System.out.println("\t______________________________________________________");
        System.out.println("\tNice seeing you! Bye!");
        System.out.println("\t______________________________________________________\n");
    }

    public static void askUser() {
        Scanner sc = new Scanner(System.in);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye") && !userInput.equals("Bye")) {
            if (userInput.equals("list") || userInput.equals("List")) {
                System.out.println("\t______________________________________________________");
                listUserCommands();
                System.out.println("\t______________________________________________________\n");
            } else {
                userCommands.add(userInput);
                System.out.println("\t______________________________________________________");
                System.out.println("\t" + userInput);
                System.out.println("\t______________________________________________________\n");
            }
            userInput = sc.nextLine();
        }
    }

    public static void listUserCommands() {
        for (int i = 0; i < userCommands.size(); i++) {
            System.out.println("\t" + String.valueOf(i + 1) + ". " + userCommands.get(i));
        }
    }
}
