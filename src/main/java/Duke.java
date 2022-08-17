import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static class DukeException extends Exception {
        String message;
        DukeException(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }
    }
    public static ArrayList<Task> list;
    public static void line() {
        System.out.println("________________________________________");
    }
    public static void greet() {
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    public static void echo(String message) {
        line();
        System.out.println(message);
        line();
    }

    private void store(String input) {
        Task t = new Task(input);
        list.add(t);
        line();
        System.out.println("added: " + t);
        line();
    }

    public static void enumerateArrayList() throws DukeException {
        int numOfTasks = list.size();
        if (numOfTasks == 0) {
            throw new DukeException("Unfortunately, you do not have any tasks at hand." +
            " Try creating some first.");
        }
        line();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            System.out.println(i + 1 + "." + t);
        }
        line();
    }

    public static Task getTask(int index) throws DukeException {
        int numOfTasks = list.size();
        if (numOfTasks == 0) {
            throw new DukeException("Unfortunately, you do not have any tasks at hand." +
                    " Try creating some first.");
        }
        if (index > numOfTasks) {
            throw new DukeException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (index < 1) {
            throw new DukeException("Hey there! Are you sure you are referring to a correct task? " +
                    "It definitely has to be at least 1!");
        }
        return list.get(index - 1);
    }

    public static void markDone(int index) throws DukeException {
        Task t = getTask(index);
        t.markDone();
        line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        line();
    }

    public static void markUndone(int index) throws DukeException {
        Task t = getTask(index);
        t.markUndone();
        line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        line();
    }

    public static void printArraySize() {
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static Todo handleTodo(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(input);
    }

    public static Deadline handleDeadline(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify what?");
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new DukeException("Did you forget to specify when your deadline for this is due by?");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateBy = secondModifiedInput[1];
        return new Deadline(description, dateBy);
    }

    public static Event handleEvent(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify what?");
        }
        String[] modifiedInput = input.split("/", 2);
        String description = modifiedInput[0];
        if (modifiedInput.length == 1) {
            throw new DukeException("Did you forget to specify when your event is at?");
        }
        String when = modifiedInput[1];
        String[] secondModifiedInput = when.split(" ", 2);
        String dateAt = secondModifiedInput[1];
        return new Event(description, dateAt);
    }

    public static void addTask(String input, Commands type) throws DukeException {
        Task t;
        switch(type) {
            case TODO: {
                t = handleTodo(input);
                break;
            }
            case DEADLINE: {
                t = handleDeadline(input);
                break;
            }
            case EVENT: {
                t = handleEvent(input);
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid task type entered");
                // this should only be seen by developer
        }
        list.add(t);
        line();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        printArraySize();
        line();
    }

    public static void deleteTask(int index) throws DukeException {
        int numOfTasks = list.size();
        if (index < 1) {
            throw new DukeException("Hey there! Are you sure you are referring to a correct task? " +
                    " It definitely has to be at least 1!");
        }
        if (index > numOfTasks) {
            throw new DukeException(String.format("That's magical! You only have %d task(s) at hand!", numOfTasks));
        }
        if (numOfTasks == 0) {
            throw new DukeException("You cant delete anything yet! Try creating some tasks first!");
        }
        Task t = getTask(index);
        int indexInList = index - 1;
        list.remove(indexInList);
        line();
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        printArraySize();
        line();
    }

    public static void handleDelete(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify which task to delete?");
        }
        int index = Integer.parseInt(input);
        deleteTask(index);
    }

    public static void exit() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        boolean isDone = false;
        list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner scanner = new Scanner(System.in); // creating scanner for user input
        while (!isDone) {
            try {
                String input = scanner.nextLine();
                String[] strArray = input.split(" ", 2);
                String first = strArray[0];
                String second = "";
                if (strArray.length == 2) {
                    second = strArray[1];
                }
                switch (first) {
                    case ("bye"): {
                        exit();
                        isDone = true;
                        break;
                    }
                    case ("list"): {
                        enumerateArrayList();
                        break;
                    }
                    case ("mark"): {
                        markDone(Integer.parseInt(second));
                        break;
                    }
                    case ("unmark"): {
                        markUndone(Integer.parseInt(second));
                        break;
                    }
                    case ("todo"): {
                        addTask(second, Commands.TODO);
                        break;
                    }
                    case ("deadline"): {
                        addTask(second, Commands.DEADLINE);
                        break;
                    }
                    case ("event"): {
                        addTask(second, Commands.EVENT);
                        break;
                    }
                    case ("delete"): {
                        handleDelete(second);
                        break;
                    }
                    default: {
                        throw new DukeException("Invalid command entered. I don't recognize it. Sorry!");
                    }
                }
            } catch (DukeException e) {
                line();
                System.out.println(e.toString());
                line();
            }
        }
    }
}
