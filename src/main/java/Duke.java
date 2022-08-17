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
    ArrayList<Task> list;
    private void line() {
        System.out.println("________________________________________");
    }
    private void greet() {
        line();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        line();
    }

    private void echo(String message) {
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

    private void enumerateArrayList() throws DukeException {
        int numOfTasks = this.list.size();
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

    private Task getTask(int index) throws DukeException {
        int numOfTasks = this.list.size();
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
        return this.list.get(index - 1);
    }

    private void markDone(int index) throws DukeException {
        Task t = getTask(index);
        t.markDone();
        line();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
        line();
    }

    private void markUndone(int index) throws DukeException {
        Task t = getTask(index);
        t.markUndone();
        line();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        line();
    }

    private void printArraySize() {
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
    }

    private void handleTodo(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(input);
        addTask(todo);
    }

    private void handleDeadline(String input) throws DukeException {
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
        Deadline deadline = new Deadline(description, dateBy);
        addTask(deadline);
    }

    private void handleEvent(String input) throws DukeException {
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
        Event event = new Event(description, dateAt);
        addTask(event);
    }

    private void addTask(Task t) {
        this.list.add(t);
        line();
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        printArraySize();
        line();
    }

    private void deleteTask(int index) throws DukeException {
        int numOfTasks = this.list.size();
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
        this.list.remove(indexInList);
        line();
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        printArraySize();
        line();
    }

    private void handleDelete(String input) throws DukeException {
        if (input.length() == 0) {
            throw new DukeException("Did you forget to specify which task to delete?");
        }
        int index = Integer.parseInt(input);
        deleteTask(index);
    }

    private void exit() {
        line();
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        boolean isDone = false;
        duke.list = new ArrayList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        duke.greet();
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
                        duke.exit();
                        isDone = true;
                        break;
                    }
                    case ("list"): {
                        duke.enumerateArrayList();
                        break;
                    }
                    case ("mark"): {
                        duke.markDone(Integer.parseInt(second));
                        break;
                    }
                    case ("unmark"): {
                        duke.markUndone(Integer.parseInt(second));
                        break;
                    }
                    case ("todo"): {
                        duke.handleTodo(second);
                        break;
                    }
                    case ("deadline"): {
                        duke.handleDeadline(second);
                        break;
                    }
                    case ("event"): {
                        duke.handleEvent(second);
                        break;
                    }
                    case ("delete"): {
                        duke.handleDelete(second);
                        break;
                    }
                    default: {
                        throw new DukeException("Invalid command entered. I don't recognize it. Sorry!");
                    }
                }
            } catch (DukeException e) {
                duke.line();
                System.out.println(e.toString());
                duke.line();
            }
        }
    }
}
