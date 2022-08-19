import java.util.Scanner;

/**
 * The Duke is a personalized chatbot.
 */
public class Duke {
    /**
     * Enum Command that represents all of Duke's commands.
     */
    private enum Command {
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE;
    }

    private static boolean terminate = false;
    private static Storage myStorage = new Storage();

    /**
     * Displays error message.
     * @param error The description for the error.
     */
    private static void displayError(String error) {
        System.out.println("☹ OOPS!!!" + error);
    }

    /**
     * Runs the given Duke command.
     * @param userInput user's input to Duke.
     */
    private static void run(String userInput) {

        String[] Strings = userInput.split(" ");
        Command cmd = Command.valueOf(Strings[0].toUpperCase());

        switch (cmd) {
            case BYE:
                terminate = true;
                break;
            case LIST:
                myStorage.printStorage();
                break;
            case MARK:
                try {
                    mark(userInput);
                } catch (IndexOutOfBoundsException e) {
                    displayError("Please enter a valid index to mark.");
                }
                break;
            case UNMARK:
                try {
                    unmark(userInput);
                } catch (IndexOutOfBoundsException e) {
                    displayError("Please enter a valid index to unmark.");
                }
                break;

            case TODO:
                try {
                    todo(userInput);
                } catch (StringIndexOutOfBoundsException e) {
                    displayError("Please enter a task todo.");
                }
                break;
            case EVENT:
                try {
                    event(userInput);
                } catch (StringIndexOutOfBoundsException e) {
                    displayError("Please enter a event.");
                } catch (DukeException e) {
                    displayError("Please only enter one event.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayError("Please use /at to specify event time.");
                }
                break;
            case DEADLINE:
                try {
                   deadline(userInput);
                } catch (StringIndexOutOfBoundsException e) {
                    displayError("Please enter a deadline.");
                } catch (DukeException e) {
                    displayError("Please only enter one deadline.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayError("Please use /by to specify deadline time.");
                }
                break;
            case DELETE:
                try {
                    delete(userInput);
                } catch (IndexOutOfBoundsException e) {
                    displayError("Sorry. Task does not exist.");
                }
        }
    }

    /**
     * Mark task as done.
     * @param userInput user's input to Duke.
     */
    private static void mark(String userInput) {
        String input = userInput.substring(5);
        myStorage.markDone(Integer.parseInt(input));
    }

    /**
     * Mark task as not done.
     * @param userInput user's input to Duke.
     */
    private static void unmark(String userInput){
        String input = userInput.substring(7);
        myStorage.unmarkDone(Integer.parseInt(input));
    }

    /**
     * Delete a task.
     * @param userInput user's input to Duke.
     */
    private static void delete(String userInput) {
        String input = userInput.substring(7);
        myStorage.deleteTask(Integer.parseInt(input));
    }

    /**
     * Calls storage to add a todo task.
     * @param userInput user's input to Duke.
     */
    private static void todo(String userInput) {
        myStorage.addTask(new ToDo(userInput.substring(5)));
    }

    /**
     * Calls storage to add an event.
     * @param userInput user's input to Duke.
     * @throws DukeException when user inputs more than one event.
     */
    private static void event(String userInput) throws DukeException {
        userInput = userInput.substring(6);
        String[] Strings = userInput.split("/at");
        if (Strings.length > 2) {
            throw new DukeException();
        }
        myStorage.addTask(new Event(Strings[0], Strings[1]));
    }

    /**
     * Calls storage to add a deadline.
     * @param userInput user's input to Duke.
     * @throws DukeException when user inputs more than one deadline.
     */
    private static void deadline(String userInput) throws DukeException {
        userInput = userInput.substring(9);
        String[] Strings = userInput.split("/by");
        if (Strings.length > 2) {
            throw new DukeException();
        }
        myStorage.addTask(new Deadline(Strings[0], Strings[1]));
    }

    /**
     * The main program for Duke.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        while (!terminate) {
            String userInput = sc.nextLine();
            try {
                run(userInput);
            } catch (IllegalArgumentException e) {
                displayError("I'm sorry, but I don't know what that means :-(");
            }

        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
