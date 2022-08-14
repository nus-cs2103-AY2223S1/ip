import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        TaskList taskList = new TaskList();

        System.out.println("Hello! I'm Pip :)\nWhat can I do for you?"); // greet the user

        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.nextLine().trim();
            try {
                if (input.equals("bye")) { // exit
                    in.close();
                    System.out.println("Goodbye and see you again soon!");
                    break;
                } else if (input.equals("list")) { // display tasks stored
                    taskList.displayTasks();
                } else if (input.startsWith("mark ")) { // mark task as done
                    try {
                        String taskNumAsString = input.substring(5);
                        int taskNum = Integer.parseInt(taskNumAsString);
                        Task task = taskList.getTask(taskNum);
                        task.markAsDone();
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        throw new InvalidTaskException();
                    } catch (InvalidTaskNumberException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.startsWith("unmark ")) { // mark task as not done
                    try {
                        String taskNumAsString = input.substring(7);
                        int taskNum = Integer.parseInt(taskNumAsString);
                        Task task = taskList.getTask(taskNum);
                        task.markAsNotDone();
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        throw new InvalidTaskException();
                    } catch (InvalidTaskNumberException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.startsWith("todo ")) { // store a todo task
                    try {
                        String description = input.substring(5);
                        taskList.addTask(new ToDo(description));
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingInfoException(true);
                    }
                } else if (input.startsWith("deadline ")) { // store a deadline task
                    try {
                        int pos = input.indexOf(" /by ");
                        String description = input.substring(9, pos).trim();
                        String by = input.substring(pos + 5);
                        taskList.addTask(new Deadline(description, by));
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingInfoException(false);
                    }
                } else if (input.startsWith("event  ")) { // store an event task
                    try {
                        int pos = input.indexOf(" /at ");
                        String description = input.substring(6, pos).trim();
                        String at = input.substring(pos + 5);
                        taskList.addTask(new Event(description, at));
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingInfoException(false);
                    }
                } else if (input.startsWith("delete ")) { // delete a task
                    try {
                        String taskNumAsString = input.substring(7);
                        int taskNum = Integer.parseInt(taskNumAsString);
                        taskList.deleteTask(taskNum);
                    } catch (IndexOutOfBoundsException | NumberFormatException e) {
                        throw new InvalidTaskException();
                    } catch (InvalidTaskNumberException e) {
                        System.out.println(e.getMessage());
                    }
                } else { // invalid input
                    throw new InvalidInputException();
                }
            } catch (InvalidTaskException | MissingInfoException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
