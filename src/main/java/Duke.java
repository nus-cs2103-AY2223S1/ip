import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Duke is a basic chat-bot that echoes whatever the user inputs.
 *
 * @author Chi Song Yi Amadeus
 * @version Level-5
 * @since 17-08-2022
 */
public class Duke {

    /**
     * Array used to store user inputs as a Task Object.
     */
    public static ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Main method initializes welcome message, and then calls taskList method.
     *
     * @param args unused.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String initMessage =
                "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     I can store a to-do list for you!\n" +
                "     Enter 'list' to display current contents and 'bye' to end the program.\n" +
                "     Start entering your tasks!\n" +
                "    ____________________________________________________________";

        System.out.println(initMessage);
        taskList();
    }

    /**
     * Function prints all items in the list chronologically entered and numbered from 1 upwards.
     *
     */
    public static void readList() {
        System.out.println("    ____________________________________________________________\n");

        for (int i = 1; i <= taskArray.size(); i++){
            String index = String.format("%d.", i);
            System.out.println(index + taskArray.get(i - 1).toString());
        }

        System.out.println("    ____________________________________________________________\n");
     }

    /**
     * taskList method creates an input loop, creating Task objects and adding it to the array.
     *
     * @throws NumberFormatException if User inputs a non integer after mark/unmark
     */
    public static void taskList() throws NumberFormatException{
        Scanner userInput = new Scanner(System.in);
        String input = userInput.nextLine();
        while (!Objects.equals(input.toLowerCase(), "bye")) {
            if (Objects.equals(input.toLowerCase(), "list")) {
                 // readList function called to display list contents
                 readList();
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                 String[] command =  input.split(" ",2);
                 String action = command[0];
                 String number  = command[1];
                 try {
                     int num = Integer.parseInt(number);
                     markTasks(action, num);
                 } catch (NumberFormatException e) {
                     System.out.println("Invalid task! Please input a number!");
                 }
            } else {
                try{
                    createTask(input);
                } catch (DukeException.EmptyTaskException | DukeException.UnkownCommandException error) {
                    System.out.println(error.getMessage());
                }

            }
            input = userInput.nextLine();
        }
        System.out.println(
                 "    ____________________________________________________________\n" +
                 "     Bye. Hope to see you again soon!\n" +
                 "    ____________________________________________________________\n");
     }

    /**
     * markTasks applies the required action on the correct task ID.
     *
     * @param action to indicate mark/unmark
     * @param index to indicate which task to apply action to
     */
    public static void markTasks(String action, int index) {
        if (index > taskArray.size() || index < 1) {
            System.out.println("Invalid task ID!");
        } else if (Objects.equals(action, "mark")){
            taskArray.get(index - 1).mark();
        } else {
            taskArray.get(index - 1).unMark();
        }
     }

    /**
     * createTask handles task of child type ToDo, Deadlines and Events.
     *
     * @param input user input.
     * @throws ArrayIndexOutOfBoundsException used to handle invalid inputs.
     * @throws DukeException.EmptyTaskException Thrown when todo task is empty.
     * @throws DukeException.UnkownCommandException Thrown when command is unknown.
     */
    public static void createTask(String input)
            throws ArrayIndexOutOfBoundsException, DukeException.EmptyTaskException, DukeException.UnkownCommandException {

        String[] commands = input.split("/", 2);
        String[] inputArr = commands[0].split(" ", 2);


        if (Objects.equals(inputArr[0], "todo")) {
            try {
                String test = inputArr[1];
                if (!(inputArr[1].trim().length() > 0)) {
                    throw new DukeException.EmptyTaskException("OOPS! please include a name for your task!");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException.EmptyTaskException("OOPS! please include a name for your task!");
            }
            ToDo newToDo = new ToDo(inputArr[1]);
            taskArray.add(newToDo);
        } else {
            if (Objects.equals(inputArr[0], "deadline") || Objects.equals(inputArr[0], "event")) {
                try {
                    String[] date = commands[1].split(" ", 2);
                    System.out.println(date[1]);

                    if (Objects.equals(inputArr[0], "deadline")) {
                        if (Objects.equals(date[0].toLowerCase(), "by")) {
                            Deadlines newDeadline= new Deadlines(inputArr[1], date[1]);
                            taskArray.add(newDeadline);
                        } else {
                            System.out.println("Include '/by' followed by a date after!");
                        }
                    } else {
                        if (Objects.equals(date[0].toLowerCase(), "at")) {
                            Events newEvent = new Events(inputArr[1], date[1]);
                            taskArray.add(newEvent);
                        } else {
                            System.out.println("Include '/at' followed by a date after!");
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("deadline/event requires date as a third parameter after /by or /at respectively!");
                }
            } else {
                throw new DukeException.UnkownCommandException("OOPS! Indicate todo/deadline/event before a task");
            }
        }

    }
}
