import java.util.Scanner;
import java.util.InputMismatchException;


public class Duke {
    static String exitWord = "bye";
    static String hLine = "\t____________________________________________";
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |    <  /\n"
            + "|____/ \\,_|_|\\_\\___|\n";

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
                else if (command.matches("mark [0-9]+") || command.matches("unmark [0-9]+")) {
                    String[] splitComm = command.split(" ");
                    String action = splitComm[0];
                    int index = Integer.parseInt(splitComm[1]) - 1;

                    changeMark(index, action);
                }
                else if (!command.equals("")) {
                    System.out.println(hLine);
                    toDoList.addTask(new Task(command));
                    System.out.println(hLine);
                }

                command = myScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\tError: please input a valid command");
                System.out.println(hLine);
                command = "";
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\tIndex specified out of range, please try again.");
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
     * @param index
     * @param action
     */
    private void changeMark(int index, String action) {
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