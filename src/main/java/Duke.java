import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String receiveCommand() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        return input;
    }

    private static void bye() {
        System.out.println("\tGoodbye! See you soon!\n" +
                           "\tAu revoir! À tout à l'heure!");
    }

    private static void add(String input, ArrayList<String> userInputs) {
        userInputs.add(input);
        System.out.println("\tadded: " + input + "\n" +
                           "\tajouté: " + input + "\n");
    }

    private static void list(ArrayList<String> userInputs) {
        for (int i = 0; i < userInputs.size(); i++) {
            System.out.println(i + ".\t" + userInputs.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I' Jean\n" +
                           "How can I help you?\n" +
                           "Bonjour! Je m'appelle Jean\n" +
                           "Vous désirez?\n");

        ArrayList<String> userInputs = new ArrayList<>();

        while(true) {
            String input = receiveCommand();
            if (input.equals("bye")) {
                bye();
                break;
            } else if (input.equals("list")) {
                list(userInputs);
            } else {
                add(input, userInputs);
            }
        }




    }
}
