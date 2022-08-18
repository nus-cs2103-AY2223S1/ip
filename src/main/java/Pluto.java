import java.util.Scanner;
import java.util.ArrayList;

public class Pluto {
    private static final String CHATBOT = "Pluto";
    public static void main(String[] args) {

        String introduction = String.format("Hello I am %s.\nWhat can I do for you?", CHATBOT);
        System.out.println(introduction);

        Scanner sc = new Scanner(System.in);
        inputCommand(sc);
    }

    public static void inputCommand(Scanner sc) {
        String command = sc.nextLine();
        String exit = "bye";
        while (!command.equals(exit)) {
            System.out.println("\t" + command);
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

