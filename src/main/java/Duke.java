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
            UserCommand command = UserCommand.valueOf(scanner.next().toUpperCase());
            switch(command) {
            case TASK:
                String taskDescription = scanner.nextLine().strip();
                storage.add(new Task(taskDescription));
                System.out.println("added: " + taskDescription);
                break;
            case LIST:
                System.out.println("Here are the tasks in your list:");
                ListIterator<Task> listIterator = storage.listIterator();
                while (listIterator.hasNext()) {
                    System.out.println(listIterator.nextIndex() + 1 + ". " + listIterator.next());
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
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                break UserInput;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        scanner.close();
    }
}
