import java.util.Scanner;
import java.io.IOException;
import java.util.InputMismatchException;


public class Duke {
    static final String EXITWORD = "bye";
    static final String HLINE = "\t____________________________________________";
    static private String filePath = "/Users/shaune/Desktop/NUS/CS2103T/Duke.txt";

    Scanner myScanner = new Scanner(System.in);
    ToDoList toDoList;

    String  command = "";

    public static void main(String[] args) {
        ToDoList toDoList = new ToDoList();
        new Duke(toDoList);
    }

    // Starts the duke program
    Duke(ToDoList toDoList) {
        this.toDoList = toDoList;

        try 
        {
            toDoList.addTaskFromFile(FileLoaderSaver.loadFile(filePath));
        }
        catch (IOException e) 
        {
            System.out.println(e.getMessage());
        }

        greet();

        while (!command.equals(EXITWORD)) {
            try {
                if (command.equals("list")) {
                    System.out.println(HLINE);
                    toDoList.listTasks();
                    System.out.println(HLINE);
                }
                else if (command.startsWith("todo")) { // create todo
                    System.out.println(HLINE);
                    addToDo(command);
                    saveFile();
                    System.out.printf("\tNow you have %d tasks in the list.\n", toDoList.getSize());
                    System.out.println(HLINE);
                }
                else if (command.startsWith("deadline")) { // create deadline
                    System.out.println(HLINE);
                    addDeadline(command);
                    saveFile();
                    System.out.printf("\tNow you have %d tasks in the list.\n", toDoList.getSize());
                    System.out.println(HLINE);
                }
                else if(command.startsWith("event")) { // create event
                    System.out.println(HLINE);
                    addEvent(command);
                    saveFile();
                    System.out.printf("\tNow you have %d tasks in the list.\n", toDoList.getSize());
                    System.out.println(HLINE);
                }
                else if (command.matches("mark [0-9]+") || command.matches("unmark [0-9]+")) { // mark
                    changeMark(command);
                    saveFile();
                }
                else if (command.matches("delete [0-9]+")) { // delete
                    deleteTask(command);
                    saveFile();
                    System.out.printf("\tNow you have %d tasks in the list.\n", toDoList.getSize());
                } else if (command != ""){
                    System.out.println(HLINE);
                    throw new Exception("I am sorry, I do not comprehend such commands. Please Try again...");
                }

                command = myScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\tError: please only input String commands.");
                System.out.println(HLINE);
                command = "";
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tIndex specified out of range, please try again...");
                System.out.println(HLINE);
                command = "";
            } catch (Exception e) {
                System.out.println("\t" + e.getMessage());
                System.out.println(HLINE);
                command = "";
            }
        }

        goodBye();
    }

    /**
     * Changes status of the task according to index given
     *
     * @param command
     */
    private void changeMark(String command) {
        String[] splitComm = command.split(" ");
        String action = splitComm[0];
        int index = Integer.parseInt(splitComm[1]) - 1;

        if (action.equals("mark")) {
            System.out.println(HLINE);
            toDoList.complete(index);
            System.out.println(HLINE);
        } else if (action.equals("unmark")){
            System.out.println(HLINE);
            toDoList.incomplete(index);
            System.out.println(HLINE);
        }
    }

    /**
     * Changes status of the task according to index given
     *
     * @param command
     */
    private void deleteTask(String command) {
        String[] splitComm = command.split(" ");
        int index = Integer.parseInt(splitComm[1]) - 1;

        System.out.println(HLINE);
        toDoList.delete(index);
        System.out.println(HLINE);
    }

    /**
     * Creates a ToDos instance and adds it to ToDoList
     *
     * @param command
     * @throws Exception
     */
    private void addToDo(String command) throws Exception {
        if (!command.matches("todo \\S.*")) {
            throw new Exception("The description of a todo cannot be empty.");
        }
        String name = command.substring(command.indexOf(" ") + 1);

        toDoList.addTask(new ToDos(name));
    }

    /**
     * Creates a Deadline instance and adds it to ToDoList
     *
     * @param command
     * @throws Exception
     */
    private void addDeadline(String command) throws Exception {
        if (!command.matches("deadline \\S.*")) {
            throw new Exception("The description of a deadline cannot be empty.");
        } else if (!command.contains("/by")) {
            throw new Exception("The description is missing a deadline.");
        }

        String details = command.substring(command.indexOf(" ") + 1);
        String name = details.split(" /by ")[0];
        String deadline = details.split(" /by ")[1];

        toDoList.addTask(new Deadline(name, deadline));
    }

    /**
     * Creates an Event instance and adds it to ToDoList
     *
     * @param command
     * @throws Exception
     */
    private void addEvent(String command) throws Exception{
        if (!command.matches("event \\S.*")) {
            throw new Exception("The description of an event cannot be empty.");
        } else if (!command.contains("/at")) {
            throw new Exception("The description is missing a time.");
        }

        String details = command.substring(command.indexOf(" ") + 1);
        String name = details.split(" /at ")[0];
        String time = details.split(" /at ")[1];

        toDoList.addTask(new Event(name, time));
    }

    // Prints generic greet message
    private static void greet() {
        System.out.println(HLINE);
        System.out.println("\tHello I'm DUKE!");
        System.out.println("\tWhat can I do for you?");
        System.out.println(HLINE);
    }

    // Prints generic goodbye message
    private static void goodBye() {
        System.out.println(HLINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(HLINE);
    }

    // Save ToDoList to file
    private void saveFile() throws IOException{
        String txtFile = toDoList.createFile();
        FileLoaderSaver.writeToFile(filePath, txtFile);
    }
}

