import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;


public class Duke {
    static String exitWord = "bye";
    static String hLine = "\t____________________________________________";
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  /\n"
            + "|____/ \\,_|_|\\_\\___|\n";

    Scanner myScanner = new Scanner(System.in);
    ArrayList<String> toDoList;
    String  command = "";

    // Prints generic greet message
    private static void greet() {
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    // Prints generic goodbye message
    private static void goodBye() {
        System.out.println(hLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(hLine);
    }

    /* Add task to todoList
     *
     * @param task
     */
    private void addTask(String task) {
        System.out.println(hLine);
        toDoList.add(task);
        System.out.println("\tadded: " + task);
        System.out.println(hLine);
    }
    // Add task to todoList
    private void listTasks() {
        System.out.println(hLine);
        for (int i = 0; i < toDoList.size(); i ++) {
            System.out.printf("\t%d. %s\n", i + 1, toDoList.get(i));
        }
        System.out.println(hLine);
    }

    // Starts the duke program
    Duke() {
        toDoList = new ArrayList<>();
        greet();

        while (!command.equals(exitWord)) {
            if (command.equals("list")) {
                listTasks();
            }
            else if (command != "") {
                addTask(command);
            }

            try {
                command = myScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\tError: please input a valid command");
                command = "";
            }
        }

        goodBye();
    }

    public static void main(String[] args) {
        new Duke();
    }
}