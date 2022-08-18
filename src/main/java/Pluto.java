import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;

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
            if (command.startsWith("todo ")) {
                String todo = command.substring(5);
                Task t = new Todo(todo);
                addTask(t, missions);
            }
            else if (command.startsWith("deadline ")) {
                String[] arr = command.substring(9).split("/by", 2);
                Task t = new Deadline(arr[0].strip(), arr[1].strip());
                addTask(t, missions);
            }
            else if (command.startsWith("event ")) {
                String[] arr = command.substring(6).split("/at", 2);
                Task t = new Event(arr[0].strip(), arr[1].strip());
                addTask(t, missions);
            }
            else if (command.startsWith("mark ")) {
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
                    String output = String.format("\t\t%d. %s",i + 1, missions.get(i).toString());
                    System.out.println(output);
                }
            }
            else {
                try {
                    getException(command);
                }
                catch (PlutoException e) {
                    System.out.println(e.getMessage());
                }
            }
            command = sc.nextLine().strip();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void addTask(Task t, ArrayList<Task> missions) {
        missions.add(t);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.toString());
        System.out.println(String.format("\tNow you have %d tasks in the list.", missions.size()));
    }

    public static void getException(String str) throws PlutoException {
        HashSet<String> commands = new HashSet<>();
        commands.add("todo");
        commands.add("deadline");
        commands.add("event");
        commands.add("mark");
        commands.add("unmark");
        if (commands.contains(str)) {
            throw new PlutoException(String.format("\tOOPS!!! The description of %s cannot be empty.", str));
        }
        throw new PlutoException("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
