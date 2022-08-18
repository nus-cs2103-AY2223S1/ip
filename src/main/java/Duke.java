import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> store = new ArrayList<>();
    private static final String[] commands = new String[] {"todo", "deadline", "event", "mark", "unmark", "delete", };


    public static void main(String[] args) {

        System.out.println("Hello! What are we gonna do today?");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (Arrays.asList(commands).contains(input.split(" ")[0])) {
                try {
                    parse(input);
                } catch (DukeWrongArgumentException | DukeNoTaskException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Pls try again");
                }
            } else if (input.equals("bye")) {
                System.out.println("\tBye! Hope that I was of service!");
                break;
            } else if (input.equals("list")) {
                int number = 1;
                for (Task task : store) {
                    if (task == null) continue;
                    System.out.println("\t" + number + "." + task);
                    number++;
                }
                System.out.println("\nYou currently have " + store.size() + " tasks in the list");
                System.out.println("Pls don't procrastinate on the above tasks!");
            } else {
                System.out.println("what's this?! REDO!!!!");
            }
        }
    }

    private static void parse(String input) throws DukeWrongArgumentException, DukeNoTaskException {
        String[] command = input.split(" ", 2);
        try {
            switch (command[0]) {
                case "mark": {
                    int index = Integer.parseInt(command[1]) - 1;
                    store.get(index).markAsDone();
                    System.out.println("\tnice! I've marked this task as done:");
                    System.out.println("\t\t" + store.get(index));
                    return;
                }
                case "unmark": {
                    int index = Integer.parseInt(command[1]) - 1;
                    store.get(index).markAsNotDone();
                    System.out.println("\tOk! I've marked this task as not done yet:");
                    System.out.println("\t\t" + store.get(index));
                    return;
                }
                case "delete": {
                    int index = Integer.parseInt(command[1]) - 1;
                    Task remove = store.remove(index);
                    System.out.println("\tOk! I've deleted this task:");
                    System.out.println("\t\t" + remove);
                    return;
                }
            }
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            if (e instanceof IndexOutOfBoundsException) {
                throw new DukeWrongArgumentException(command[1] + " is not a valid index", e);
            } else if (e instanceof NumberFormatException) {
                throw new DukeWrongArgumentException("Unknown index '" + command[1] + "'", e);
            }
        }

        if (command[0].equals("todo")) {
            try{
                store.add(new ToDo((command[1])));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeWrongArgumentException("The proper command is: todo [description]", e);
            }
        } else if (command[0].equals("deadline")) {
            try {
                String[] desc = command[1].split(" /by ");
                store.add(new Deadline(desc[0], desc[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeWrongArgumentException("The proper command is: deadline [description] /by [date]", e);
            }
        } else {
            try {
                String[] desc = command[1].split(" /at ");
                store.add(new Event(desc[0], desc[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeWrongArgumentException("The proper command is: event [description] /at [date]", e);
            }

        }
        System.out.println("\tadded: " + store.get(store.size() - 1));
        System.out.println("You now have " + store.size() + " tasks in the list");
    }
}
