import java.util.Scanner;
import java.util.ArrayList;

public class Pluto {
    private static final String CHATBOT = "Pluto";
    public static void main(String[] args) {

        String introduction = String.format("\tHello I am %s.\n\tWhat can I do for you?", CHATBOT);
        System.out.println(introduction);

        Scanner sc = new Scanner(System.in);
        inputCommand(sc);
    }

    public static void inputCommand(Scanner sc) {
        ArrayList<String> missions = new ArrayList<>();
        String command = sc.nextLine();
        String exit = "bye";
        while (!command.equals(exit)) {
            switch(command) {
                case "list":
                    for (int i = 0; i < missions.size(); i++) {
                        String output = String.format("\t%d. %s",i + 1, missions.get(i));
                        System.out.println(output);
                    }
                    break;
                default:
                    missions.add(command);
                    System.out.println("\tadded: " + command);
            }
            command = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}


