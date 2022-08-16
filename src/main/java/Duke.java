import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {

        ArrayList<Task> myList = new ArrayList<>();
        boolean bye = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello my name is uncle raymond");

        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;
                UserInterface.sayBye();
            } else if (input.equals("list")) {
                UserInterface.printList(myList);
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                EventHandler.markTask(taskIndex, myList);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                EventHandler.unmarkTask(taskIndex, myList);
            } else if (input.startsWith("todo")) {
                if (input.length() > 4) {
                    EventHandler.addTodo(input, myList);
                } else {
                    System.out.println("The description of a todo cannot be empty");
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            } else if (input.startsWith("deadline")) {
                if (input.length() > 8) {
                    EventHandler.addDeadline(input, myList);
                } else {
                    System.out.println("The description of a deadline cannot be empty");
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
            } else if (input.startsWith("event")) {
                if (input.length() > 5) {
                    EventHandler.addEvent(input, myList);
                } else {
                    System.out.println("The description of an event cannot be empty");
                    throw new DukeException("The description of an event cannot be empty.");
                }
            } else {
                System.out.println("I'm sorry, but I don't know what that means.");
                throw new DukeException("Unknown command");
            }

        }

        scanner.close();
    }
}
