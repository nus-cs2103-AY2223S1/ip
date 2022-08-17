import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static final Task[] store = new Task[100];
    private static int count = 0;
    private static final String[] commands = new String[] {"todo", "deadline", "event", "mark", "unmark", };

    public static void main(String[] args) {

        System.out.println("Hello! What are we gonna do today?");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (Arrays.asList(commands).contains(input.split(" ")[0])) {
                parse(input);
            } else if (input.equals("bye")) {
                System.out.println("\tBye! Hope that I helped!");
                break;
            } else if (input.equals("list")) {
                int number = 1;
                for (Task task : store) {
                    if (task == null) continue;
                    System.out.println("\t" + number + "." + task);
                    number++;
                }
            } else {
                System.out.println(input);
            }
        }
    }

    private static void parse(String input) {
        String[] command = input.split(" ", 2);
        if (command[0].equals("mark")) {
            int index = Integer.parseInt(command[1]);
            store[index].markAsDone();
            System.out.println("\tnice! I've marked this task as done:");
            System.out.println("\t\t" + store[index]);
            return;
        } else if (command[0].equals("unmark")) {
            int index = Integer.parseInt(command[1]);
            store[index].markAsNotDone();
            System.out.println("\tOk! I've marked this task as not done yet:");
            System.out.println("\t\t" + store[index]);
            return;
        }

        if (command[0].equals("todo")) {
            store[++count] = new ToDo(command[1]);
        } else if (command[0].equals("deadline")) {
            String[] desc = command[1].split(" /by ");
            store[++count] = new Deadline(desc[0], desc[1]);
        } else {
            String[] desc = command[1].split(" /at ");
            store[++count] = new Event(desc[0], desc[1]);
        }

        System.out.println("\tadded: " + store[count]);
        System.out.println("You now have " + count + " tasks in the list");
    }
}
