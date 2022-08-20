import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> storage = new ArrayList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        UserInput: while (scanner.hasNextLine()) {
            UserCommand command;
            try {
                command = UserCommand.valueOf(scanner.next().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means:-(");
                scanner.nextLine();
                continue;
            }
            switch (command) {
            case TODO:
                String toDoDescription = scanner.nextLine().strip();
                if (toDoDescription.isBlank()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                    continue;
                }
                ToDo toDoTask = new ToDo(toDoDescription);
                storage.add(toDoTask);
                System.out.println("Got it. I've added this task:\n" + toDoTask
                        + "\nNow you have " + storage.size() + " tasks in your list.");
                break;
            case DEADLINE:
                scanner.useDelimiter("/by");
                String deadlineDescription = scanner.next().strip();
                scanner.reset().skip("/by");
                String deadlineBy = scanner.nextLine().strip();
                Deadline deadlineTask = new Deadline(deadlineDescription, deadlineBy);
                storage.add(deadlineTask);
                System.out.println("Got it. I've added this task:\n" + deadlineTask
                        + "\nNow you have " + storage.size() + " tasks in the list.");
                break;
            case EVENT:
                scanner.useDelimiter("/at");
                String eventDescription = scanner.next().strip();
                scanner.reset().skip("/at");
                String eventAt = scanner.nextLine().strip();
                Event eventTask = new Event(eventDescription, eventAt);
                storage.add(eventTask);
                System.out.println("Got it. I've added this task:\n" + eventTask
                        + "\nNow you have " + storage.size() + " tasks in the list.");
                break;
            case LIST:
                System.out.println("Here are the tasks in your list:");
                ListIterator<Task> listIterator = storage.listIterator();
                while (listIterator.hasNext()) {
                    System.out.println(listIterator.nextIndex() + 1 + "." + listIterator.next());
                }
                break;
            case MARK:
                int markIndex = scanner.nextInt();
                storage.get(markIndex - 1).markAsDone();
                break;
            case UNMARK:
                int unmarkIndex = scanner.nextInt();
                storage.get(unmarkIndex - 1).markAsUndone();
                break;
            case DELETE:
                int deleteIndex = scanner.nextInt();
                Task deletedTask = storage.get(deleteIndex - 1);
                storage.remove(deletedTask);
                System.out.println("Noted. I've removed this task:\n" + deletedTask
                        + "\nNow you have " + storage.size() + " tasks in the list.");
                break;
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                break UserInput;
            }
        }
        scanner.close();
    }
}
