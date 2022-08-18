import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final ArrayList<Task> store = new ArrayList<>();
    enum COMMANDS {todo, deadline, event, mark, unmark, delete, }


    public static void main(String[] args) {

        System.out.println("Hello! What are we gonna do today?");
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (isCommand(input.split(" ")[0])) {
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

    private static boolean isCommand(String input) {
        for (COMMANDS c : COMMANDS.values()) {
            if (c.name().equals(input)) return true;
        }
        return false;
    }

    private static void parse(String input) throws DukeWrongArgumentException, DukeNoTaskException {
        String[] arr = input.split(" ", 2);
        COMMANDS command = COMMANDS.valueOf(arr[0]);
        try {
            switch (command) {
                case mark: {
                    int index = Integer.parseInt(arr[1]) - 1;
                    store.get(index).markAsDone();
                    System.out.println("\tnice! I've marked this task as done:");
                    System.out.println("\t\t" + store.get(index));
                    return;
                }
                case unmark: {
                    int index = Integer.parseInt(arr[1]) - 1;
                    store.get(index).markAsNotDone();
                    System.out.println("\tOk! I've marked this task as not done yet:");
                    System.out.println("\t\t" + store.get(index));
                    return;
                }
                case delete: {
                    int index = Integer.parseInt(arr[1]) - 1;
                    Task remove = store.remove(index);
                    System.out.println("\tOk! I've deleted this task:");
                    System.out.println("\t\t" + remove);
                    return;
                }
            }
        } catch (NullPointerException | NumberFormatException | IndexOutOfBoundsException e) {
            if (e instanceof IndexOutOfBoundsException) {
                throw new DukeWrongArgumentException(arr[1] + " is not a valid index", e);
            } else if (e instanceof NumberFormatException) {
                throw new DukeWrongArgumentException("Unknown index '" + arr[1] + "'", e);
            }
        }

        switch (command) {
            case todo: {
                try {
                    store.add(new ToDo((arr[1])));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeWrongArgumentException("The proper command is: todo [description]", e);
                }
                break;
            }
            case deadline: {
                try {
                    String[] desc = arr[1].split(" /by ");
                    store.add(new Deadline(desc[0], desc[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeWrongArgumentException("The proper command is: deadline [description] /by [date]", e);
                }
                break;
            }
            case event: {
                try {
                    String[] desc = arr[1].split(" /at ");
                    store.add(new Event(desc[0], desc[1]));
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new DukeWrongArgumentException("The proper command is: event [description] /at [date]", e);
                }
                break;
            }
        }
        System.out.println("\tadded: " + store.get(store.size() - 1));
        System.out.println("You now have " + store.size() + " tasks in the list");
    }
}
