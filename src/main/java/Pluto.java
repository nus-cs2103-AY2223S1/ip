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
        ArrayList<Task> missions = new ArrayList<>();
        String command = sc.nextLine().strip();
        String exit = "bye";
        while (!command.equals(exit)) {
            if (command.startsWith("mark ")) {
                int idx = Integer.parseInt(command.substring(5).strip());
                Task t = missions.get(idx - 1);
                t.markAsDone();

            }
            else if (command.startsWith("unmark ")) {
                int idx = Integer.parseInt(command.substring(7).strip());
                Task t = missions.get(idx - 1);
                t.markAsUndone();
            }
            else if(command.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < missions.size(); i++) {
                    String output = String.format("\t%d. %s",i + 1, missions.get(i).toString());
                    System.out.println(output);
                }
            }
            else {
                Task t = new Task(command);
                missions.add(t);
                System.out.println("\tadded: " + t);
            }
            command = sc.nextLine().strip();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}


