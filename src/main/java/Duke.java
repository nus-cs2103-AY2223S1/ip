import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    /**
     * Prints the string representation of all tasks that has been
     * added to the arraylist via the Task toString method.
     * @param inputList
     */
    public static void printList(ArrayList<Task> inputList) {
        Task[] inputArray = inputList.toArray(new Task[inputList.size()]);
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= inputArray.length; i++) {
            String item = String.format("%s. %s", i, inputArray[i-1].toString());
            System.out.println(item);
        }
    }

    /**
     * Marks the task as completed via the index of the task on the
     * arraylist. The method throws an ArrayIndexOutOfBoundsException if the
     * task is found to be non-existent.
     * @param parts
     * @param inputList
     * @throws ArrayIndexOutOfBoundsException
     */
    public static void markTask(String[] parts, ArrayList<Task> inputList) throws DukeException {
        if (parts.length == 1) {
            throw new DukeException("Please specify the index of the task (i.e. mark 2).");
        } else if (Integer.parseInt(parts[1]) == 0 || Integer.parseInt(parts[1]) > inputList.size()) {
            throw new DukeException("There is no such task!");
        } else {
            int index = Integer.parseInt(parts[1]);
            Task task = inputList.get(index - 1);
            System.out.println(task.markAsDone());
        }
    }

    /**
     * Adds the task to arraylist, and formats a String representation
     * that is to be printed along with the task representation and size of list.
     * @param task
     * @param inputList
     */
    public static void taskAdd(Task task, ArrayList<Task> inputList) {
        inputList.add(task);
        String output = String.format("Got it. I've added this task:\n%s\nNow you have %s tasks in the list.",
                task.toString(),
                inputList.size());
        System.out.println(output);
    }

    /**
     * Abstracts the creation of a todo object, with exception handling.
     * @param input
     * @return Todo object
     * @throws DukeException thrown if there is no description.
     */
    public static Todo createTodo(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a todo cannot be empty.");
        } else {
            Todo todo = new Todo(taskType[1]);
            return todo;
        }
    }

    /**
     * Abstracts the creation of a Deadline object, with exception handling.
     * @param input
     * @return Deadline object
     * @throws DukeException thrown if there is no description or /by field.
     */
    public static Deadline createDeadline(String input) throws DukeException{
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (taskType[1].split("/by ", 2).length == 1) {
            throw new DukeException("The /by field cannot be empty.");
        } else {
            String[] taskBy = taskType[1].split("/by ", 2);
            Deadline deadline = new Deadline(taskBy[0], taskBy[1]);
            return deadline;
        }
    }

    /**
     * Abstracts the creation of a Event object, with exception handling.
     * @param input
     * @return Event object
     * @throws DukeException thrown if there is no description or /at field
     */
    public static Event createEvent(String input) throws DukeException {
        String[] taskType = input.split(" ", 2);
        if (taskType.length == 1) {
            throw new DukeException("The description of a event cannot be empty.");
        } else if (taskType[1].split("/at ", 2).length == 1) {
            throw new DukeException("The /at field cannot be empty.");
        } else {
            String[] taskBy = taskType[1].split("/at ", 2);
            Event event = new Event(taskBy[0], taskBy[1]);
            return event;
        }
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> inputList = new ArrayList<Task>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        String input = myObj.nextLine();
        String[] parts = input.split(" ");

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList(inputList);
            } else if (parts[0].equals("mark")) {
                try {
                    markTask(parts, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (parts[0].equals("todo")) {
                try {
                    Task todo = createTodo(input);
                    taskAdd(todo, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (parts[0].equals("deadline")) {
                try {
                    Deadline deadline = createDeadline(input);
                    taskAdd(deadline, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (parts[0].equals("event")) {
                try {
                    Event event = createEvent(input);
                    taskAdd(event, inputList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("I'm sorry, but I don't know what that means :(");
            }
            input = myObj.nextLine();
            parts = input.split(" ");
        }

        myObj.close();
        System.out.println("Bye. Hope to see you again soon!");
    }
}
