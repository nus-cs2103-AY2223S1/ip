import java.util.Scanner;
import java.util.InputMismatchException;


public class Duke {
    static String exitWord = "bye";
    static String hLine = "\t____________________________________________";

    Scanner myScanner = new Scanner(System.in);
    ToDoList toDoList;

    String  command = "";

    // Starts the duke program
    Duke(ToDoList toDoList) {
        this.toDoList = toDoList;

        greet();

        while (!command.equals(exitWord)) {
            try {
                if (command.equals("list")) {
                    System.out.println(hLine);
                    toDoList.listTasks();
                    System.out.println(hLine);
                }
                else if (command.startsWith("todo")) {
                    System.out.println(hLine);
                    addToDo(command);
                    System.out.printf("\tNow you have %d tasks in the list.\n", toDoList.getSize());
                    System.out.println(hLine);
                }
                else if (command.startsWith("deadline")) {
                    System.out.println(hLine);
                    addDeadline(command);
                    System.out.println(hLine);
                }
                else if(command.startsWith("event")) {
                    System.out.println(hLine);
                    addEvent(command);
                    System.out.printf("\tNow you have %d tasks in the list.\n", toDoList.getSize());
                    System.out.println(hLine);
                }
                else if (command.matches("mark [0-9]+") || command.matches("unmark [0-9]+")) {
                    changeMark(command);
                }
                else if (command.matches("delete [0-9]+")) {
                    deleteTask(command);
                    System.out.printf("\tNow you have %d tasks in the list.\n", toDoList.getSize());
                } else if (command != ""){
                    System.out.println(hLine);
                    throw new Exception("I am sorry, I do not comprehend such commands. Please Try again...");
                }

                command = myScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\tError: please only input String commands.");
                System.out.println(hLine);
                command = "";
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tIndex specified out of range, please try again...");
                System.out.println(hLine);
                command = "";
            } catch (Exception e) {
                System.out.println("\t" + e.getMessage());
                System.out.println(hLine);
                command = "";
            }
        }

        goodBye();
    }

    /* Changes status of the task according to index given
     *
     * @param command
     */
    private void changeMark(String command) {
        String[] splitComm = command.split(" ");
        String action = splitComm[0];
        int index = Integer.parseInt(splitComm[1]) - 1;

        if (action.equals("mark")) {
            System.out.println(hLine);
            toDoList.complete(index);
            System.out.println(hLine);
        } else if (action.equals("unmark")){
            System.out.println(hLine);
            toDoList.incomplete(index);
            System.out.println(hLine);
        }
    }

    /* Changes status of the task according to index given
     *
     * @param command
     */
    private void deleteTask(String command) {
        String[] splitComm = command.split(" ");
        int index = Integer.parseInt(splitComm[1]) - 1;

        System.out.println(hLine);
        toDoList.delete(index);
        System.out.println(hLine);
    }

    /* Creates a ToDos instance and adds it to ToDoList
     *
     * @param command
     */
    private void addToDo(String command) throws Exception {
        if (!command.matches("todo \\S.*")) {
            throw new Exception("The description of a todo cannot be empty.");
        }
        String name = command.substring(command.indexOf(" ") + 1);

        toDoList.addTask(new ToDos(name));
    }

    /* Creates a Deadline instance and adds it to ToDoList
     *
     * @param command
     */
    private void addDeadline(String command) throws Exception {
        if (!command.matches("deadline \\S.*")) {
            throw new Exception("The description of a deadline cannot be empty.");
        } else if (!command.contains("/by")) {
            throw new Exception("The description is missing a deadline.");
        }

        String details = command.substring(command.indexOf(" ") + 1);
        String name = details.split("/by ")[0];
        String deadline = details.split("/by ")[1];

        toDoList.addTask(new Deadline(name, deadline));
    }

    /* Creates an Event instance and adds it to ToDoList
     *
     * @param command
     */
    private void addEvent(String command) throws Exception{
        if (!command.matches("event \\S.*")) {
            throw new Exception("The description of an event cannot be empty.");
        } else if (!command.contains("/at")) {
            throw new Exception("The description is missing a time.");
        }

        String details = command.substring(command.indexOf(" ") + 1);
        String name = details.split("/at ")[0];
        String time = details.split("/at ")[1];

        toDoList.addTask(new Event(name, time));
    }

    // Prints generic greet message
    private static void greet() {
        System.out.println(hLine);
        System.out.println("\tHello I'm DUKE!");
        System.out.println("\tWhat can I do for you?");
        System.out.println(hLine);
    }

    // Prints generic goodbye message
    private static void goodBye() {
        System.out.println(hLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(hLine);
    }

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        new Duke(toDoList);
    }
}