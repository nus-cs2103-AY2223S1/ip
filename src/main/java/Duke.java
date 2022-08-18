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
                System.out.println("\nYou currently have " + count + " tasks in the list");
                System.out.println("Pls don't procrastinate on the above tasks!");
            } else {
                System.out.println("what's this?! REDO!!!!");
            }
        }
    }

    private static void parse(String input) throws DukeWrongArgumentException, DukeNoTaskException {
        String[] command = input.split(" ", 2);
        try {
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
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            if (input.length() == 1) {
                throw new DukeWrongArgumentException("The proper command is: mark [index]", e);
            } else if (!Character.isDigit(command[1].charAt(0))) {
                throw new DukeWrongArgumentException("Unknown index '" + command[1] + "'", e);
            } else if (store[Integer.parseInt(command[1])] == null) {
                throw new DukeNoTaskException("You don't have that many tasks haha", e);
            }
        }

        if (command[0].equals("todo")) {
            try{
                ToDo task = new ToDo((command[1]));
                store[++count] = task;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeWrongArgumentException("The proper command is: todo [description]", e);
            }
        } else if (command[0].equals("deadline")) {
            try {
                String[] desc = command[1].split(" /by ");
                Deadline task = new Deadline(desc[0], desc[1]);
                store[++count] = task;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeWrongArgumentException("The proper command is: deadline [description] /by [date]", e);
            }
        } else {
            try {
                String[] desc = command[1].split(" /at ");
                Event task = new Event(desc[0], desc[1]);
                store[++count] = task;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeWrongArgumentException("The proper command is: event [description] /at [date]", e);
            }

        }
        System.out.println("\tadded: " + store[count]);
        System.out.println("You now have " + count + " tasks in the list");
    }
}
