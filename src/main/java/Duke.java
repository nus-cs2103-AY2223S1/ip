import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        initDuke();
    }

    // Duke Initializer starts the duke program
    private static void initDuke() {
        Scanner myScanner = new Scanner(System.in);
        String exitWord = "bye";
        String hLine = "\t____________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");

        String  command = "";
        ArrayList<String> toDoList = new ArrayList<>();

        while (!command.equals(exitWord)) {
            if (command.equals("list")) {
                System.out.println(hLine);
                for (int i = 0; i < toDoList.size(); i ++) {
                    System.out.printf("\t%d. %s\n", i + 1, toDoList.get(i));
                }
                System.out.println(hLine);

            }
            else if (command != "") {
                System.out.println(hLine);
                toDoList.add(command);
                System.out.println("\tadded: " + command);
                System.out.println(hLine);
            }
            try {
                command = myScanner.nextLine();
            } catch (java.util.InputMismatchException e) {
                System.out.println("\tError: please input a valid command");
                command = "";
            }
        }
        System.out.println(hLine);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(hLine);
    }
}