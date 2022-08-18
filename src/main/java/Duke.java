import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // List to store text entered by the user and display them back to the user when requested
    private static final ArrayList<Task> wordList = new ArrayList<>();

    /**
     * Start the program
     */
    private static void greet() {
        System.out.println("Hello I am\n" + Constants.LOGO);
        System.out.println("May I help you?");
    }

    /**
     * Exit when user type 'bye'
     */
    private static void exit() {
        System.out.println("Great that you joined. See you soon. Bye!");
    }

    /**
     * Add text that user typed to the word list
     * @param task text the user typed
     */
    private static void add(Task task) {
        System.out.println(Constants.ARROW + "Added task: " + task.toString());
        Duke.wordList.add(task);
        System.out.println("Now you have " + wordList.size() + " task(s) on your list.");
    }

    /**
     * Delete a task
     * @param task text the user typed
     */
    private static void delete(Task task) {
        System.out.println(Constants.ARROW + "Deleted task: " + task.toString());
        Duke.wordList.remove(task);
        System.out.println("Now you have " + wordList.size() + " task(s) on your list.");
    }

    /**
     * Print all item in the word list
     */
    private static void listItems() {
        System.out.println(Constants.LISTING_MESSAGE);
        for (int i = 0; i < wordList.size(); i++) {
            System.out.println((i+1) + ") " + wordList.get(i).toString());
        }
    }

    public static void main(String[] args){
        // Greeting
        Duke.greet();

        // User Input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        // Processing
        while (!userInput.equals(Constants.EXIT)) {
            String typeOfTask = userInput.split(" ")[0];
            int index;
            switch (typeOfTask) {
                case Constants.LIST:
                    Duke.listItems();
                    break;
                case Constants.UNMARK:
                    try {
                        userInput.substring(8);
                        index = Integer.parseInt(userInput.split(" ")[1]);
                        wordList.get(index - 1).unmark();
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    } catch (NumberFormatException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    }
                    break;
                case Constants.MARK:
                    try {
                        userInput.substring(6);
                        index = Integer.parseInt(userInput.split(" ")[1]);
                        wordList.get(index - 1).markAsDone();
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    } catch (NumberFormatException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        new DukeException.EmptyMarkingException();
                        break;
                    }
                    break;
                case Constants.TODO:
                    try {
                        // Error when to-do followed by a blank space
                        userInput.substring(6);
                        // Error when just to-do
                        Duke.add(new ToDo(userInput.substring(5)));
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyTodoException();
                        break;
                    }
                    break;
                case Constants.DEADLINE:
                    try {
                        // Error when deadline followed by a blank space
                        userInput.substring(10);
                        // Error when just deadline
                        String[] deadline = userInput.substring(9).split(" /by ");
                        Duke.add(new Deadline(deadline[0], deadline[1]));
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyDeadlineException();
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        new DukeException.DeadlineWithoutByException();
                    }
                    break;
                case Constants.EVENT:
                    try {
                        // Error when event followed by a blank space
                        userInput.substring(7);
                        // Error when just event
                        String[] event = userInput.substring(6).split(" /at ");
                        Duke.add(new Event(event[0], event[1]));
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyEventException();
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        new DukeException.EventWithoutAtException();
                    }
                    break;
                case Constants.DELETE:
                    try {
                        userInput.substring(8);
                        index =  Integer.parseInt(userInput.split(" ")[1]);
                        Duke.delete(Duke.wordList.get(index-1));
                    } catch (StringIndexOutOfBoundsException e) {
                        new DukeException.EmptyDeleteException();
                        break;
                    } catch (NumberFormatException e) {
                        new DukeException.EmptyDeleteException();
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        new DukeException.EmptyDeleteException();
                        break;
                    }
                    break;
                default:
                    new DukeException.InvalidInputException();
                    break;
            }
            userInput = scanner.nextLine();
        }

        // Bye
        Duke.exit();
    }
}
