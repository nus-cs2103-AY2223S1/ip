import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        initDuke();
    }

    private static void initDuke() {
            Scanner myScanner = new Scanner(System.in);
            String exitWord = "bye";
            String hLine = "\t____________________________________________";
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            System.out.println("What can i do for you?");

            String  command = "";

            while ( !command.equals(exitWord)) {
                if (command.equals("")) {
                    System.out.println(hLine);
                    System.out.println("\tadded: " + command);
                    System.out.println(hLine);
                }

                command = myScanner.nextLine();
            }
            System.out.println(hLine);
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println(hLine);
    }
}
