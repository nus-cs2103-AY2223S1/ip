import java.util.Scanner;

public class Duke {

    private static TaskList taskList = new TaskList();
    private static final Scanner sc = new Scanner(System.in);

    public static void printMessage(String input) throws DukeException {
        if(input.equalsIgnoreCase("bye")) {
            System.out.println("Goodbye!");
            System.exit(0);
        } else if(input.equalsIgnoreCase("list")) {
            taskList.list();
        } else if(input.matches("^todo.*")) {
            try {
                ToDo todo = new ToDo(input.substring(5));
                taskList.add(todo);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
        } else if(input.matches("^deadline.*")) {
            try {
                String[] str = input.substring(9).split(" /by ");
                Deadline deadline = new Deadline(str[0], str[1]);
                taskList.add(deadline);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description and/or the time of a deadline cannot be empty.");
            }
        } else if(input.matches("^event.*")) {
            try {
                String[] str = input.substring(6).split(" /at ");
                Event event = new Event(str[0], str[1]);
                taskList.add(event);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The description and/or the time of an event cannot be empty.");
            }
        } else if(input.matches("^mark [0-9]*$")) {
            try {
                int index = Integer.parseInt(input.substring(5)) - 1;
                taskList.markTaskAsDone(index);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + taskList.getTask(index));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You cannot mark a non-existent task as done.");
            }
        } else if(input.matches("^unmark [0-9]$")) {
            try {
                int index = Integer.parseInt(input.substring(7)) - 1;
                taskList.markTaskAsUndone(index);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " + taskList.getTask(index));
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! You cannot mark a non-existent task as undone.");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
        }
    }

    public static void main(String[] args) {
        String welcomeMsg = "Hi there! Baymax at your service.";
        System.out.println(welcomeMsg);
        String input = sc.nextLine();

        while(true) {
            try {
                printMessage(input);
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                input = sc.nextLine();
            }
        }
    }
}
